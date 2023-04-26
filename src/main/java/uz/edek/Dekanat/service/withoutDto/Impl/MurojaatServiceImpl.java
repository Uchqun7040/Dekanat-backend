package uz.edek.Dekanat.service.withoutDto.Impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BarcodeQRCode;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.edek.Dekanat.entity.FileStorage;
import uz.edek.Dekanat.entity.Murojaat;
import uz.edek.Dekanat.repository.DistributedRepository;
import uz.edek.Dekanat.repository.FileStorageRepository;
import uz.edek.Dekanat.repository.MurojaatRepository;
import uz.edek.Dekanat.service.withoutDto.FileStorageService;
import uz.edek.Dekanat.service.withoutDto.MurojaatService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
public class MurojaatServiceImpl extends AbstractService<Murojaat> implements MurojaatService {
    @Value("${upload.folder}")
    private String uploadFolder;
    @Autowired
    MurojaatRepository murojaatRepository;

    @Autowired
    FileStorageRepository fileStorageRepository;

    @Autowired
    FileStorageService fileStorageService;

    public MurojaatServiceImpl(DistributedRepository<Murojaat> repository) {
        super(repository);
    }

    @Override
    public void someChangesForCreate(Murojaat entity){
        entity.setFayl(createPDF(entity));
    }

    @Override
    public void someChangesForUpdate(Murojaat entity) {
        entity.setFayl(createPDF(entity));
    }

    @Override
    public List<Murojaat> getAllByTalabaId(Long talabaId) {
        return murojaatRepository.findAllByTalabaIdOrderByIdDesc(talabaId);
    }

    public String createPDF(Murojaat murojaat){
        String hashId = "";
        if(!Objects.equals(murojaat.getFayl(),"")){
            fileStorageService.delete(murojaat.getFayl());
        }
        try{
            String fileName=uploadFolder+"/temporary/murojaat"+murojaat.getTalaba().getId()+".pdf";
            createDocument(murojaat,fileName,false);
            File file = new File(fileName);
            InputStream inputStream = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file","murojaat"+murojaat.getId()+".pdf","application/pdf" , inputStream);
            hashId =fileStorageService.save(multipartFile);
            if(murojaat.getHolat().ordinal() == 0){
                murojaat.setFayl(hashId);
                createDocument(murojaat,fileName,true);
                File file2 = new File(fileName);
                InputStream inputStream2 = new FileInputStream(file2);
                MultipartFile multipartFile2 = new MockMultipartFile("file","murojaat"+murojaat.getId()+".pdf","application/pdf" , inputStream2);
                fileStorageService.changeFile(fileStorageService.findByHashId(hashId),multipartFile2);
                FileStorage fileStorage=fileStorageService.findByHashId(hashId);
                fileStorage.setFileSize(multipartFile2.getSize());
                fileStorageRepository.save(fileStorage);
            }

        }catch (Exception e){
            System.out.println("PDF yaratishda xatolik mavjud bo'ldi!");
        }

        return hashId;
    }

    public void createDocument(Murojaat entity, String fileName, boolean m){
        try{
            Document document=new Document();
            PdfWriter.getInstance(document,new FileOutputStream(fileName));
            document.open();
            String tempT = "Qarshi Davlat Universiteti Matematika va kompyuter ilmlari fakulteti dekani "
                    + entity.getOqituvchi().getFamiliya()
                    +" "
                    + entity.getOqituvchi().getIsm()
                    +"ga "
                    + entity.getTalaba().getYonalish()
                    + " yo'nalishi "
                    + entity.getTalaba().getGuruh()
                    + "-guruh talabasi "
                    + entity.getTalaba().getFamiliya()
                    + " "
                    + entity.getTalaba().getIsm()
                    + " nomidan";
            Paragraph temp=new Paragraph(tempT.toUpperCase());
            temp.setIndentationLeft(280);
            document.add(temp);

            Paragraph mavzu=new Paragraph("\n\n"+entity.getMavzu().toUpperCase()+"\n");
            mavzu.setAlignment(Element.ALIGN_CENTER);
            document.add(mavzu);

            Paragraph matn=new Paragraph(entity.getMatn());
            document.add(matn);

            String created=entity.getCreated().toString();
            String sanaT = created.substring(0,created.indexOf("T"));
            String vaqtT = created.substring(created.indexOf("T")+1,created.indexOf("T")+6);
            created = "Murojaat vaqti: "+sanaT+", "+vaqtT;
            Paragraph vaqt=new Paragraph(created);
            document.add(vaqt);

            int n=entity.getHolat().ordinal();
            String holatT = "Murojaat holati: ";
            if(n==0){
                holatT+="Tasdiqlandi!";
            }else if(n==1){
                holatT+="Rad etildi!";
            }else{
                holatT+="O'rganilmoqda";
            }
            Paragraph holat=new Paragraph(holatT);
            document.add(holat);

            String xulosaT="O'qituvchining xulosasi: ";
            if (Objects.equals(entity.getXulosa(), "")){
                xulosaT+="mavjud emas.";
            }else{
                xulosaT+="\n\b\b\b"+ entity.getXulosa();
            }
            Paragraph xulosa=new Paragraph(xulosaT);
            document.add(xulosa);

            if(n==0 && m){
                FileStorage fileStorage =fileStorageService.findByHashId(entity.getFayl());
                BarcodeQRCode qr_code = new BarcodeQRCode("https://api.e-dek.uz/api/file/download/"+fileStorage.getHashId(), 1, 1, null);
                Image img= qr_code.getImage();
                document.add(img);
            }

            document.close();
        }catch (Exception e){
            System.out.println("Document yaratishda xatolik mavjud bo'ldi!");
        }
    }

    public void createQR(String content) throws Exception {
        String path=uploadFolder+"/"+content+".jpg";
        BitMatrix matrix=new MultiFormatWriter()
                .encode(uploadFolder, BarcodeFormat.QR_CODE,200,200);

        MatrixToImageWriter.writeToPath(matrix,"jpg", Paths.get(path));
    }
}

//    public String createPDF(Murojaat murojaat){
//        String hashId = "";
//        if(!Objects.equals(murojaat.getFayl(),"")){
//            fileStorageService.delete(murojaat.getFayl());
//        }
//        try{
//            String fileName=uploadFolder+"/temporary/murojaat"+murojaat.getId()+".pdf";
//            createDocument(murojaat,fileName,false);
//            File file = new File(fileName);
//            InputStream inputStream = new FileInputStream(file);
//            MultipartFile multipartFile = new MockMultipartFile("file","murojaat"+murojaat.getId()+".pdf","application/pdf" , inputStream);
//            hashId =fileStorageService.save(multipartFile);
//            murojaat.setFayl(hashId);
//            createDocument(murojaat,fileName,true);
//            File file2 = new File(fileName);
//            InputStream inputStream2 = new FileInputStream(file2);
//            MultipartFile multipartFile2 = new MockMultipartFile("file","murojaat"+murojaat.getId()+".pdf","application/pdf" , inputStream2);
//            fileStorageService.changeFile(fileStorageService.findByHashId(hashId),multipartFile2);
//        }catch (Exception e){
//            System.out.println("Fayl yaratishda xatolik mavjud bo'ldi!");
//        }
//
//        return hashId;
//    }

//    public void createDocument(Murojaat entity, String fileName, boolean m){
//        try{
//            Document document=new Document();
//            PdfWriter.getInstance(document,new FileOutputStream(fileName));
//            document.open();
//            String tempT = "Qarshi Davlat Universiteti Matematika va kompyuter ilmlari fakulteti dekani"
//                    + entity.getOqituvchi().getFamiliya()
//                    +" "
//                    + entity.getOqituvchi().getIsm()
//                    +"ga "
//                    + entity.getTalaba().getFamiliya()
//                    + " "
//                    + entity.getTalaba().getIsm()
//                    + " nomidan";
//            Paragraph temp=new Paragraph(tempT.toUpperCase());
//            temp.setIndentationLeft(300);
//            document.add(temp);
//
//            Paragraph mavzu=new Paragraph(entity.getMavzu().toUpperCase());
//            mavzu.setAlignment(Element.ALIGN_CENTER);
//            document.add(mavzu);
//
//            Paragraph matn=new Paragraph(entity.getMatn());
//            document.add(matn);
//
//            String created=entity.getCreated().toString();
//            String sanaT = created.substring(0,created.indexOf("T"));
//            String vaqtT = created.substring(created.indexOf("T")+1,created.indexOf("T")+6);
//            created = "Murojaat vaqti: "+sanaT+", "+vaqtT;
//            Paragraph vaqt=new Paragraph(created);
//            document.add(vaqt);
//
//            int n=entity.getHolat().ordinal();
//            String holatT = "Murojaat holati: ";
//            if(n==0){
//                holatT+="Tasdiqlandi!";
//            }else if(n==1){
//                holatT+="Rad etildi!";
//            }else{
//                holatT+="O'rganilmoqda";
//            }
//            Paragraph holat=new Paragraph(holatT);
//            document.add(holat);
//
//            String xulosaT="O'qituvchining xulosasi: ";
//            if (Objects.equals(entity.getXulosa(), "")){
//                xulosaT+="mavjud emas.";
//            }else{
//                xulosaT+="\n\b\b\b"+ entity.getXulosa();
//            }
//            Paragraph xulosa=new Paragraph(xulosaT);
//            document.add(xulosa);
//
//            if(n==0 && m){
//                FileStorage fileStorage =fileStorageService.findByHashId(entity.getFayl());
////                BitMatrix matrix=new MultiFormatWriter()
////                        .encode(uploadFolder+"/"+fileStorage.getUploadPath(), BarcodeFormat.QR_CODE,200,200);
//                BarcodeQRCode qr_code = new BarcodeQRCode(uploadFolder+"/"+fileStorage.getUploadPath(), 1, 1, null);
//                Image img= qr_code.getImage();
//                document.add(img);
//            }
//
//            document.close();
//        }catch (Exception e){
//            System.out.println("Fayl yaratishda xatolik mavjud bo'ldi!");
//        }
//    }

//    public void createQR(String content) throws Exception {
//        String path=uploadFolder+"/"+content+".jpg";
//        BitMatrix matrix=new MultiFormatWriter()
//                .encode(uploadFolder, BarcodeFormat.QR_CODE,200,200);
//
//        MatrixToImageWriter.writeToPath(matrix,"jpg", Paths.get(path));
//    }


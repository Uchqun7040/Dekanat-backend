package uz.edek.Dekanat.service.withoutDto;



import org.springframework.web.multipart.MultipartFile;
import uz.edek.Dekanat.entity.FileStorage;

public interface FileStorageService {

    public String save(MultipartFile multiFile);

    public FileStorage findByHashId(String hashId);

    public void delete(String hashId);

    public String ext(String fullName);

    public void changeFile(FileStorage fileStorage, MultipartFile multipartFile);

}

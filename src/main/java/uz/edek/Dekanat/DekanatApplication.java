package uz.edek.Dekanat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uz.edek.Dekanat.entity.Kafedra;
import uz.edek.Dekanat.entity.Lavozim;
import uz.edek.Dekanat.entity.Oqituvchi;
import uz.edek.Dekanat.service.withoutDto.KafedraService;
import uz.edek.Dekanat.service.withoutDto.OqituvchiService;

@SpringBootApplication
public class DekanatApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(DekanatApplication.class, args);
	}

	@Autowired
	KafedraService kafedraService;

	@Autowired
	OqituvchiService oqituvchiService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if(kafedraService.getById(1L) ==null){
			Kafedra ka=new Kafedra();
			ka.setNom("Amaliy matematika");
			kafedraService.create(ka);
		}

		if(oqituvchiService.getById(1L) == null) {
			Oqituvchi oqituvchi = new Oqituvchi();
			oqituvchi.setIsm("Norxol");
			oqituvchi.setFamiliya("Eshqorayeva");
			oqituvchi.setLavozim(Lavozim.DEKAN);
			oqituvchi.setKafedra(kafedraService.getById(1L));
			oqituvchi.setTel("+912151771");
			oqituvchi.setMutaxasislik("Matematika");
			oqituvchiService.create(oqituvchi);
		}
	}


}

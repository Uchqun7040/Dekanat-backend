package uz.edek.Dekanat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.edek.Dekanat.entity.Kafedra;
import uz.edek.Dekanat.entity.Lavozim;
import uz.edek.Dekanat.entity.Oqituvchi;
import uz.edek.Dekanat.repository.OqituvchiRepository;
import uz.edek.Dekanat.service.withDto.OqituvchiService;
import uz.edek.Dekanat.service.withoutDto.KafedraService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DekanatApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(DekanatApplication.class, args);
	}

	@Autowired
	KafedraService kafedraService;

	@Autowired
	OqituvchiRepository oqituvchiRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if(kafedraService.getById(1L) ==null){
			Kafedra ka=new Kafedra();
			ka.setNom("Amaliy matematika");
			kafedraService.create(ka);
		}

		if(!oqituvchiRepository.findByLogin("dekan123").isPresent()) {
			Set<Lavozim> lavozims=new HashSet<>();
			lavozims.add(Lavozim.DEKAN);

			Oqituvchi oqituvchi = new Oqituvchi();
			oqituvchi.setIsm("Norxol");
			oqituvchi.setFamiliya("Eshqorayeva");
			oqituvchi.setLavozim(lavozims);
			oqituvchi.setLogin("dekan123");
			oqituvchi.setParol(passwordEncoder.encode("dekan123"));
			oqituvchi.setKafedra(kafedraService.getById(1L));
			oqituvchi.setTel("+912151771");
			oqituvchi.setCreated(LocalDateTime.now());
			oqituvchi.setMutaxasislik("Matematika");
			oqituvchiRepository.save(oqituvchi);
		}

		if(!oqituvchiRepository.findByLogin("admin123").isPresent()) {
			Set<Lavozim> lavozims=new HashSet<>();
			lavozims.add(Lavozim.ADMIN);

			Oqituvchi oqituvchi = new Oqituvchi();
			oqituvchi.setIsm("Uchqun");
			oqituvchi.setFamiliya("Shonazarov");
			oqituvchi.setLavozim(lavozims);
			oqituvchi.setLogin("admin123");
			oqituvchi.setParol(passwordEncoder.encode("admin123"));
			oqituvchi.setTel("+912207040");
			oqituvchi.setCreated(LocalDateTime.now());
			oqituvchi.setMutaxasislik("Dasturchi");
			oqituvchiRepository.save(oqituvchi);
		}
	}


}

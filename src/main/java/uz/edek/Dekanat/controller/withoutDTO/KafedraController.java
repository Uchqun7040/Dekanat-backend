package uz.edek.Dekanat.controller.withoutDTO;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.edek.Dekanat.entity.Kafedra;
import uz.edek.Dekanat.service.withoutDto.CommonService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api/kafedra")
public class KafedraController extends AbstractController<Kafedra>{
    public KafedraController(CommonService<Kafedra> service) {
        super(service);
    }
}

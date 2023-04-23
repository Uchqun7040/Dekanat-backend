package uz.edek.Dekanat.controller.withoutDTO;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.edek.Dekanat.entity.Talaba;
import uz.edek.Dekanat.service.withoutDto.CommonService;

@CrossOrigin(value = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/talaba")
public class TalabaController extends AbstractController<Talaba>{
    public TalabaController(CommonService<Talaba> service) {
        super(service);
    }

}

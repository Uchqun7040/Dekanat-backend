package uz.edek.Dekanat.controller.withoutDTO;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.edek.Dekanat.entity.Oqituvchi;
import uz.edek.Dekanat.service.withoutDto.CommonService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/oqituvchi")
public class OqituvchiController extends AbstractController<Oqituvchi>{
    public OqituvchiController(CommonService<Oqituvchi> service) {
        super(service);
    }
}

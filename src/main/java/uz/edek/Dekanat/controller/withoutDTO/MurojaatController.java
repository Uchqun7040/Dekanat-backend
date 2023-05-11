package uz.edek.Dekanat.controller.withoutDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.edek.Dekanat.entity.Murojaat;
import uz.edek.Dekanat.service.withoutDto.CommonService;
import uz.edek.Dekanat.service.withoutDto.MurojaatService;

import java.util.List;

@RestController
@RequestMapping("/api/murojaat")
public class MurojaatController extends AbstractController<Murojaat>{

    @Autowired
    MurojaatService murojaatService;

    public MurojaatController(CommonService<Murojaat> service) {
        super(service);
    }

    @GetMapping("/getByTalaba/{id}")
    public List<Murojaat> getAllByTalaba(@PathVariable Long id){
        return murojaatService.getAllByTalabaId(id);
    }
    @GetMapping("/getByOqituvchi/{id}")
    public List<Murojaat> getAllByOqituvchi(@PathVariable Long id){
        return murojaatService.getAllByTalabaId(id);
    }
}

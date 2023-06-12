package uz.edek.Dekanat.controller.withoutDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.edek.Dekanat.entity.Talaba;
import uz.edek.Dekanat.service.withoutDto.CommonService;
import uz.edek.Dekanat.service.withoutDto.TalabaService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api/talaba")
public class TalabaController extends AbstractController<Talaba>{
    @Autowired
    TalabaService talabaService;
    public TalabaController(CommonService<Talaba> service) {
        super(service);
    }
    @GetMapping("/byHemisId/{id}")
    public ResponseEntity<?> getByHemisId(@PathVariable String id){
        return ResponseEntity.ok(talabaService.getByHemisId(id));
    }
}

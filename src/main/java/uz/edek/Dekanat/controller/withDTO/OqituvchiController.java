package uz.edek.Dekanat.controller.withDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.edek.Dekanat.dto.OqituvchiDTO;
import uz.edek.Dekanat.entity.Oqituvchi;
import uz.edek.Dekanat.service.withDto.CommonServiceDto;
import uz.edek.Dekanat.service.withDto.OqituvchiService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api/oqituvchi")
public class OqituvchiController extends AbstractDTOController<Oqituvchi, OqituvchiDTO>{

    @Autowired
    OqituvchiService oqituvchiService;
    public OqituvchiController(CommonServiceDto service) {
        super(service);
    }

    @GetMapping("/dekans")
    public ResponseEntity<?> getAllDekan(){
        return ResponseEntity.ok(oqituvchiService.getAllDekan());
    }
}

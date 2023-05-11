package uz.edek.Dekanat.service.withDto;

import uz.edek.Dekanat.dto.OqituvchiDTO;
import uz.edek.Dekanat.entity.Oqituvchi;
import uz.edek.Dekanat.vm.OqituvchiVM;

import java.util.List;

public interface OqituvchiService extends CommonServiceDto<Oqituvchi, OqituvchiDTO>{
    OqituvchiDTO getCurrentUser();
    boolean changePassword(OqituvchiVM oqituvchiVM);
    List<OqituvchiDTO> getAllDekan();
}

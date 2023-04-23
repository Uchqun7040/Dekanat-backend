package uz.edek.Dekanat.service.withoutDto;


import uz.edek.Dekanat.entity.Murojaat;

import java.util.List;

public interface MurojaatService extends CommonService<Murojaat> {
    List<Murojaat> getAllByTalabaId(Long talabaId);
}

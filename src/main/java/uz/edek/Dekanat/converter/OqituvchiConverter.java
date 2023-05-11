package uz.edek.Dekanat.converter;

import org.springframework.stereotype.Component;
import uz.edek.Dekanat.dto.OqituvchiDTO;
import uz.edek.Dekanat.entity.Oqituvchi;
@Component
public class OqituvchiConverter extends AbstractDTOConverter<Oqituvchi, OqituvchiDTO>{
    @Override
    public OqituvchiDTO convert(Oqituvchi entity) {
        OqituvchiDTO oqituvchiDTO = new OqituvchiDTO(entity);
        return oqituvchiDTO;
    }
}

package uz.edek.Dekanat.converter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import uz.edek.Dekanat.dto.BaseDTO;
import uz.edek.Dekanat.entity.DistributedEntity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractDTOConverter<ENTITY extends DistributedEntity, DTO extends BaseDTO> {

    public abstract DTO convert(ENTITY entity);

    public Page<DTO> convertPage(final Page<ENTITY> list){
        if(list.isEmpty()){
           return Page.empty();
        }

        return new PageImpl<>(list.stream().map(this::convert).collect(Collectors.toList()));
    }

    public List<DTO> convertList(final List<ENTITY> list){
        if(list.isEmpty()){
            return null;
        }
        return list.stream().map(this::convert).collect(Collectors.toList());
    }

}

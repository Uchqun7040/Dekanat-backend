package uz.edek.Dekanat.service.withDto;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.edek.Dekanat.dto.BaseDTO;
import uz.edek.Dekanat.entity.DistributedEntity;

public interface CommonServiceDto<ENTITY extends DistributedEntity, DTO extends BaseDTO>{
    public Page<DTO> getAll(Pageable pageable);
    public DTO create(ENTITY entity) throws Exception;
    public DTO update (ENTITY entity);
    public DTO getById(Long id);
    public boolean delete(Long id);
}

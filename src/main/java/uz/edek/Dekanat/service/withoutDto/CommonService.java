package uz.edek.Dekanat.service.withoutDto;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.edek.Dekanat.entity.DistributedEntity;

import java.util.List;

public interface CommonService<ENTITY extends DistributedEntity>{
    public Page<ENTITY> getAll(Pageable pageable);
    public ENTITY create(ENTITY entity) throws Exception;
    public ENTITY update (ENTITY entity);
    public ENTITY getById(Long id);
    public boolean delete(Long id);
    public List<ENTITY> getAllList();
}

package uz.edek.Dekanat.repository;


import org.springframework.stereotype.Repository;
import uz.edek.Dekanat.entity.Murojaat;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MurojaatRepository extends DistributedRepository<Murojaat>{
    List<Murojaat> findAllByTalabaIdOrderByIdDesc(Long id);
    List<Murojaat> findAllByOqituvchiIdOrderByIdDesc(Long id);
}

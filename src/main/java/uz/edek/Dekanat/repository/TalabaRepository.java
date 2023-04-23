package uz.edek.Dekanat.repository;

import org.springframework.stereotype.Repository;
import uz.edek.Dekanat.entity.Talaba;

@Repository
public interface TalabaRepository extends DistributedRepository<Talaba>{
    public Talaba findByHemisId(String hemisId);
    public Talaba getById(Long id);
    public Boolean existsTalabaByHemisId(String hemisid);
}

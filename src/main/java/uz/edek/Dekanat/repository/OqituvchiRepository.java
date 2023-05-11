package uz.edek.Dekanat.repository;


import org.springframework.stereotype.Repository;
import uz.edek.Dekanat.entity.Lavozim;
import uz.edek.Dekanat.entity.Oqituvchi;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface OqituvchiRepository extends DistributedRepository<Oqituvchi>{
    Optional<Oqituvchi> findByLogin(String login);

    List<Oqituvchi> findAllByLavozimInOrderByFamiliyaAsc(Collection<Lavozim> lavozimlar);
}

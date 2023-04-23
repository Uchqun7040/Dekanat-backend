package uz.edek.Dekanat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.transaction.Transactional;
import java.util.List;

@NoRepositoryBean
@Transactional
public interface DistributedRepository<ENTITY> extends JpaRepository<ENTITY, Long> {
    public List<ENTITY> findAllByOrderByIdDesc();
}

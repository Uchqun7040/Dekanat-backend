package uz.edek.Dekanat.service.withoutDto.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.edek.Dekanat.entity.Talaba;
import uz.edek.Dekanat.repository.TalabaRepository;
import uz.edek.Dekanat.service.withoutDto.TalabaService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TalabaServiceImpl  implements TalabaService {

    @Autowired
    TalabaRepository talabaRepository;

    @Override
    public Page<Talaba> getAll(Pageable pageable) {
        return talabaRepository.findAll(pageable);
    }

    @Override
    public Talaba create(Talaba entity) throws Exception {
        if(!talabaRepository.existsTalabaByHemisId(entity.getHemisId())){
            entity.setCreated(LocalDateTime.now());
            return talabaRepository.save(entity);
        }
        return talabaRepository.findByHemisId(entity.getHemisId());
    }

    @Override
    public Talaba update(Talaba entity) {
        return null;
    }

    @Override
    public Talaba getById(Long id) {
        return talabaRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<Talaba> getAllList() {
        return talabaRepository.findAllByOrderByIdDesc();
    }
}

package uz.edek.Dekanat.service.withoutDto.Impl;


import org.springframework.stereotype.Service;
import uz.edek.Dekanat.entity.Oqituvchi;
import uz.edek.Dekanat.repository.DistributedRepository;
import uz.edek.Dekanat.service.withoutDto.OqituvchiService;

@Service
public class OqituvchiServiceImpl extends AbstractService<Oqituvchi> implements OqituvchiService {
    public OqituvchiServiceImpl(DistributedRepository<Oqituvchi> repository) {
        super(repository);
    }

    @Override
    public void someChangesForCreate(Oqituvchi entity) {

    }

    @Override
    public void someChangesForUpdate(Oqituvchi entity) {

    }
}

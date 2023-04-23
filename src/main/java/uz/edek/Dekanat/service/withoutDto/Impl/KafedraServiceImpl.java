package uz.edek.Dekanat.service.withoutDto.Impl;


import org.springframework.stereotype.Service;
import uz.edek.Dekanat.entity.Kafedra;
import uz.edek.Dekanat.repository.DistributedRepository;
import uz.edek.Dekanat.service.withoutDto.KafedraService;

@Service
public class KafedraServiceImpl extends AbstractService<Kafedra> implements KafedraService {

    public KafedraServiceImpl(DistributedRepository<Kafedra> repository) {
        super(repository);
    }

    @Override
    public void someChangesForCreate(Kafedra entity) {

    }

    @Override
    public void someChangesForUpdate(Kafedra entity) {

    }
}

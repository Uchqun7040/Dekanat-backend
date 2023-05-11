package uz.edek.Dekanat.service.withDto.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.edek.Dekanat.converter.AbstractDTOConverter;
import uz.edek.Dekanat.converter.OqituvchiConverter;
import uz.edek.Dekanat.dto.OqituvchiDTO;
import uz.edek.Dekanat.entity.Lavozim;
import uz.edek.Dekanat.entity.Oqituvchi;
import uz.edek.Dekanat.repository.DistributedRepository;
import uz.edek.Dekanat.repository.OqituvchiRepository;
import uz.edek.Dekanat.service.withDto.OqituvchiService;
import uz.edek.Dekanat.vm.OqituvchiVM;

import java.util.*;

@Service
public class OqituvchiServiceImpl extends AbstractDTOService<Oqituvchi,OqituvchiDTO> implements OqituvchiService {

    @Autowired
    OqituvchiConverter oqituvchiConverter;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    OqituvchiRepository oqituvchiRepository;

    public OqituvchiServiceImpl(DistributedRepository<Oqituvchi> repository, AbstractDTOConverter<Oqituvchi, OqituvchiDTO> converter) {
        super(repository, converter);
    }


    @Override
    public void someChangesForCreate(Oqituvchi entity) {
        entity.setParol(encoder.encode(entity.getParol()));
        Set<Lavozim> lavozims = new HashSet<>();
        lavozims.add(Lavozim.OQITUVCHI);
        entity.setLavozim(lavozims);
    }

    @Override
    public void someChangesForUpdate(Oqituvchi entity) {

    }

    @Override
    public OqituvchiDTO getCurrentUser() {
        String username = getPrincipal();
        if (username != null)
            return oqituvchiRepository.findByLogin(username).map(OqituvchiDTO::new).orElse(null);
        return null;
    }

    @Override
    public boolean changePassword(OqituvchiVM oqituvchiVM) {
        Optional<Oqituvchi> oqituvchi = oqituvchiRepository.findByLogin(oqituvchiVM.getUsername());
        if(oqituvchi.isPresent() && encoder.matches(oqituvchiVM.getOldPassword(),oqituvchi.get().getParol())){
            oqituvchi.get().setParol(encoder.encode(oqituvchiVM.getNewPassword()));
            oqituvchiRepository.save(oqituvchi.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<OqituvchiDTO> getAllDekan() {
        List<Oqituvchi> dekanlar = oqituvchiRepository.findAllByLavozimInOrderByFamiliyaAsc(List.of(Lavozim.DEKAN));
        return oqituvchiConverter.convertList(dekanlar);
    }


    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}

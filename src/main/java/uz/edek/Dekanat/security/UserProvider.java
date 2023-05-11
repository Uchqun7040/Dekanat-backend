package uz.edek.Dekanat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.edek.Dekanat.entity.Oqituvchi;
import uz.edek.Dekanat.repository.OqituvchiRepository;
import java.util.Optional;

@Service
public class UserProvider implements UserDetailsService {

    @Autowired
    OqituvchiRepository oqituvchiRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Oqituvchi> oqituvchi =  oqituvchiRepository.findByLogin(username);
        if(oqituvchi.isPresent()){
            return new UserSpecial(oqituvchi.get());
        }
        throw new UsernameNotFoundException(username);
    }
}
package uz.edek.Dekanat.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.edek.Dekanat.entity.Oqituvchi;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSpecial implements UserDetails {

    private String username;
    private String password;
    private Set<SimpleGrantedAuthority> roles;
    private Boolean active;

    public UserSpecial(){}

    public UserSpecial(Oqituvchi oqituvchi){
        this.username = oqituvchi.getLogin();
        this.password = oqituvchi.getParol();

        this.roles = oqituvchi.getLavozim()
                        .stream()
                        .map(l->new SimpleGrantedAuthority(l.name()))
                        .collect(Collectors.toSet());
        this.active = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }


}


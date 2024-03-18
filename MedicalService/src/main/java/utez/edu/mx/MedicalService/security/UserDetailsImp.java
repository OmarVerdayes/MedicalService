package utez.edu.mx.MedicalService.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import utez.edu.mx.MedicalService.models.users.Users;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor

public class UserDetailsImp implements UserDetails {

    public final Users user;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Obtener el rol del usuario y convertirlo en un GrantedAuthority
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRol().getName());

        // Devolver una lista de autoridades que contenga el rol del usuario
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
        return user.getStatus().getName().equals("Activo") ? true : false;
    }

}

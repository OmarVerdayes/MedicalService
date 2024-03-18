package utez.edu.mx.MedicalService.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import utez.edu.mx.MedicalService.models.users.Users;
import utez.edu.mx.MedicalService.models.users.UsersRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    UsersRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user=repository.getOneByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("El usuario no existe"));

        return new UserDetailsImp(user);
    }
}

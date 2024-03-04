package utez.edu.mx.MedicalService.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import utez.edu.mx.MedicalService.models.users.Users;
import utez.edu.mx.MedicalService.models.users.UsersRepository;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Users> user = userRepository.getOneByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("Usuario no encontrado",null);
        } else if(!"Activo".equals(user.get().getStatus().getName())){
            throw new UsernameNotFoundException("Usuario inactivo",null);
        }
        return new org.springframework.security.core.userdetails.User(user.get().getEmail(),user.get().getPassword(), new ArrayList<>());
    }
}

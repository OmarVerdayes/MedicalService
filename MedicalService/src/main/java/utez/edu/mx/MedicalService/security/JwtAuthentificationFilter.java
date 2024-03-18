package utez.edu.mx.MedicalService.security;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import utez.edu.mx.MedicalService.models.users.Users;
import utez.edu.mx.MedicalService.models.users.UsersRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class JwtAuthentificationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        AuthCredentials authCredentials=new AuthCredentials();

        try{
            authCredentials=new ObjectMapper().readValue(request.getReader(),AuthCredentials.class);
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken usernamePAT= new UsernamePasswordAuthenticationToken(
                authCredentials.getEmail(),
                authCredentials.getPassword(),
                Collections.emptyList()
        );

        return getAuthenticationManager().authenticate(usernamePAT);
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        UserDetailsImp userDetailsImp=(UserDetailsImp)authResult.getPrincipal();
        String token =TokenUtils.createToken(userDetailsImp.getUsername());

        response.addHeader("Authorization","Bearer "+token);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}

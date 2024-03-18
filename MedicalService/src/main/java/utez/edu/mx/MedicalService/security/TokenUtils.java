package utez.edu.mx.MedicalService.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    //@Value("${app.security.signing.authorization.token}")
    private static String  SECRET="a813mnaosih897S41SAOIUHQOIHKBZQF543647930040ligspq9zuweo723o97LIHASGEF87463HBSD";
    //@Value("${app.security.expiration.time.seconds.token}")
    private static Long expiration_time=2592000L;


    public static String createToken (String email){

        long expiration_time_seconds=expiration_time*1000;

        Date expitation_date= new Date(System.currentTimeMillis()+expiration_time_seconds);

        Map<String,Object> extra= new HashMap<>();


        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expitation_date)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .compact();
    }

    public  static UsernamePasswordAuthenticationToken getAuthentification (String token){
       try{
           Claims claims =Jwts.parserBuilder()
                   .setSigningKey(SECRET.getBytes())
                   .build()
                   .parseClaimsJws(token)
                   .getBody();

           String email= claims.getSubject();
           return new UsernamePasswordAuthenticationToken(email,null, Collections.emptyList());

       }catch (JwtException e){
           return null;
       }
    }


}

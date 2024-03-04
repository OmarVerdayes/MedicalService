package utez.edu.mx.MedicalService.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthenticationDTO {

    private String email;

    private String password;

}

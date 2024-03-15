package utez.edu.mx.MedicalService.controllers.patient.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.MedicalService.models.patient.Patient;
import utez.edu.mx.MedicalService.models.users.Users;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientDTO {
    private Long id;
    @NotNull(message="El NSS es obligatoria")
    @NotBlank(message = "El NSS es obligatoria")
    @Size(min = 11, max = 11, message = "El NSS debe tener 11 digitos")
    private String nss;

    @NotNull(message="La descripcion de las alergias es obligatoria")
    @NotBlank(message = "La descripcion de las alergias es obligatoria")
    @Size(min = 2,  message = "El descripcion de las alergias debe tener 2 caracteres como minimo (NO)")
    private String allergies;

    private Users user;


    public Patient castToOriginalObject(){
        return new Patient(id,nss,allergies,user);
    }
}

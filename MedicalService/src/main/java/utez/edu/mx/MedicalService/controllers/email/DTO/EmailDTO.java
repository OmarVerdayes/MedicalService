package utez.edu.mx.MedicalService.controllers.email.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmailDTO {
    @NotBlank(message = "El correo de destino es obligatorio")
    @Size(min = 3, max = 90, message = "El correo de destino debe tener entre 3 y 90 caracteres")
    private String emailUser;
    @NotBlank(message = "El mensaje es obligatorio")
    @Size(min = 3, max = 255, message = "El correo de destino debe tener entre 3 y 255 caracteres")
    private String message;


}

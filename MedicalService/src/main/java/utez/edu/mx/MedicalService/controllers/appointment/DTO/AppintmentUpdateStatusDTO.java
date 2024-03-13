package utez.edu.mx.MedicalService.controllers.appointment.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppintmentUpdateStatusDTO {
    @NotBlank(message = "El id de la cita es obligatorio")
    @Min( value = 0L,message = "El id de la cita no puede ser menor a 0")
    private Long id_appointment;
    @NotBlank(message = "El id del nuevo estatus es obligatorio")
    @Min( value = 0L,message = "El id del nuevo estatus no puede ser menor a 0")
    private Long id_new_status;
}

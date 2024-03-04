package utez.edu.mx.MedicalService.controllers.appointmentStatus;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.MedicalService.models.appointmentStatus.AppointmentStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentStatusDTO {
    private Long id;
    @NotNull(message="La fecha de inicio es obligatoria")
    @NotBlank(message = "La fecha de inicio es obligatoria")
    @Size(min = 3, max = 60, message = "El comentario debe tener entre 3 y 60 caracteres")
    private String name;


    public AppointmentStatus castToOriginalObject(){
        return new AppointmentStatus(id,name);
    }
}

package utez.edu.mx.MedicalService.controllers.appointmentStatus.DTO;

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
    @NotNull(message="El nombre es obligatoria")
    @NotBlank(message = "El nombre de inicio es obligatoria")
    @Size(min = 3, max = 60, message = "El nombre debe tener entre 3 y 60 caracteres")
    private String name;


    public AppointmentStatus castToOriginalObject(){
        return new AppointmentStatus(id,name);
    }
}

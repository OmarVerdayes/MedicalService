package utez.edu.mx.MedicalService.controllers.appointment.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IdAppointmentDTO {
    @NotNull(message="El id de la cita es obligatoria")
    @Min(value = 0, message = "El id de la cita no puede ser menor a 0")
    private Long id_appointment;
}

package utez.edu.mx.MedicalService.controllers.appointment_comments.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.MedicalService.models.appointment.Appointment;
import utez.edu.mx.MedicalService.models.appointment_comments.Appointment_comments;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment_commentsDTO {

    private Long id;
    @NotNull(message="El comentario es obligatorio")
    @NotEmpty(message = "El comentario es obligatorio")
    @Size(min = 3, max = 300, message = "El comentario debe tener entre 3 y 300 caracteres")
    private String comment;
    @NotNull(message = "La calificación es obligatoria")
    @Digits(integer = 1, fraction = 0, message = "La calificación debe ser un número entero")
    @Min(value = 1, message = "La calificación debe ser igual o mayor que 1")
    @Max(value = 5, message = "La calificación debe ser igual o menor que 5")
    private int qualification;
    private Appointment appointment;

    public Appointment_comments castToOriginalObject(){
        return new Appointment_comments(id,comment,qualification,appointment);
    }

}

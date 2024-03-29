package utez.edu.mx.MedicalService.controllers.doctors.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.MedicalService.models.doctors.Doctors;
import utez.edu.mx.MedicalService.models.users.Users;

import java.sql.Time;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoctorDTO {
    private Long id;
    @NotNull(message="El numero de decula profecional es obligatoria")
    @NotBlank(message = "El numero de decula profecional es obligatoria")
    @Size(min = 7, max = 20, message = "El numero de decula profecional debe tener entre 7 y 20 caracteres")
    private String id_number;

    @NotNull(message="El numero de consultorio profecional es obligatoria")
    @NotBlank(message = "El numero de consultorio profecional es obligatoria")
    @Min(value = 1,message = "El numero de consultorio debe sere minimo 1")
    @Max(value = 999,message = "El numero de consultorio deber ser menor a mil")
    private Long consulting_room;

    @NotNull(message="La hora de inicio de turno es obligatoria")
    @NotBlank(message = "La hora de inicio de turno es obligatoria")
    @Pattern(regexp = "\\d{2}:\\d{2}", message = "La hora de inicio de turno debe estar en formato HH:mm")
    private Time shift_start_time;

    @NotNull(message="La hora de fin de turno es obligatoria")
    @NotBlank(message = "La hora de fin de turno es obligatoria")
    @Pattern(regexp = "\\d{2}:\\d{2}", message = "La hora de inicio de fin debe estar en formato HH:mm")
    private Time shift_finish_time;

    private Users user;

    public Doctors castToOriginalObject(){
        return new Doctors(id,id_number,consulting_room,shift_start_time,shift_finish_time,user);
    }

}

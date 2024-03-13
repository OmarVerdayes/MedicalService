package utez.edu.mx.MedicalService.controllers.appointment.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.MedicalService.models.appointment.Appointment;
import utez.edu.mx.MedicalService.models.appointmentStatus.AppointmentStatus;
import utez.edu.mx.MedicalService.models.doctors.Doctors;
import utez.edu.mx.MedicalService.models.patient.Patient;
import utez.edu.mx.MedicalService.models.speciality.Speciality;
import utez.edu.mx.MedicalService.models.users.Users;

import java.awt.geom.Area;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentDTO {
    private Long id;

    @NotNull(message="La fecha de inicio es obligatoria")
    @NotBlank(message = "La fecha de inicio es obligatoria")
    @Future(message = "La fecha de inicio tiene que ser despues de la fecha actual")
    private String start_date;

    @NotNull(message="La fecha de inicio es obligatoria")
    @NotBlank(message = "La fecha de inicio es obligatoria")
    @Future(message = "La fecha de inicio tiene que ser despues de la fecha actual")
    private String final_date;

    @Size(min = 3, max = 60, message = "El comentario debe tener entre 3 y 60 caracteres")
    private String comments;



    private Speciality speciality;
    private AppointmentStatus appointmentStatus;
    private Doctors doctor;
    private Patient patient;



    public Appointment castToOriginalObject(){
        return new Appointment(id,start_date,final_date,comments,speciality,appointmentStatus,doctor,patient);
    }
}


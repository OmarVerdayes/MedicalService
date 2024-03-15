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

    @NotNull(message="La fecha de fin es obligatoria")
    @NotBlank(message = "La fecha de fin es obligatoria")
    @Future(message = "La fecha de fin tiene que ser despues de la fecha actual")
    private String final_date;

    private Speciality speciality;
    private AppointmentStatus appointmentStatus;
    private Doctors doctor;
    private Patient patient;

    public Appointment castToOriginalObject(){
        return new Appointment(id,start_date,final_date,speciality,appointmentStatus,doctor,patient);
    }
}


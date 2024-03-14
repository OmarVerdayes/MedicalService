package utez.edu.mx.MedicalService.models.appointment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.MedicalService.models.appointmentStatus.AppointmentStatus;
import utez.edu.mx.MedicalService.models.doctors.Doctors;
import utez.edu.mx.MedicalService.models.patient.Patient;
import utez.edu.mx.MedicalService.models.speciality.Speciality;
import utez.edu.mx.MedicalService.models.users.Users;

@Entity
@Table(name="appointment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( columnDefinition = "DATETIME",nullable = false)
    private String start_date;
    @Column( columnDefinition = "DATETIME",nullable = false)
    private String final_date;
    @ManyToOne
    @JoinColumn(name = "id_speciality",nullable = false)
    private Speciality speciality;
    @ManyToOne
    @JoinColumn(name = "id_appointmentStatus",nullable = false)
    private AppointmentStatus appointmentStatus;
    @ManyToOne
    @JoinColumn(name = "id_doctor",nullable = false)
    private Doctors doctor;
    @ManyToOne
    @JoinColumn(name = "id_patient",nullable = false)
    private Patient patient;
}

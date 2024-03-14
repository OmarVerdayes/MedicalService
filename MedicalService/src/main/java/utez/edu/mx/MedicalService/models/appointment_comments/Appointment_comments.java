package utez.edu.mx.MedicalService.models.appointment_comments;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.MedicalService.models.appointment.Appointment;

@Entity
@Table(name="appointment_comments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment_comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( columnDefinition = "text",nullable = false)
    private String comment;
    @Column( columnDefinition = "int",nullable = false)
    private int qualification;
    @ManyToOne
    @JoinColumn(name = "id_appointment",nullable = false)
    private Appointment appointment;
}

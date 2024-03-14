package utez.edu.mx.MedicalService.models.doctor_comments;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.MedicalService.models.doctors.Doctors;
import utez.edu.mx.MedicalService.models.users.Users;

@Entity
@Table(name="doctor_comments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Doctor_comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( columnDefinition = "text",nullable = false)
    private String comment;
    @Column( columnDefinition = "int",nullable = false)
    private int qualification;
    @ManyToOne
    @JoinColumn(name = "id_doctor",nullable = false)
    private Doctors doctor;
}

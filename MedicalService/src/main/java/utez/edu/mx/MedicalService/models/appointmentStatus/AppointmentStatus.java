package utez.edu.mx.MedicalService.models.appointmentStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="appointmentstatus")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( columnDefinition = "varchar(60)",nullable = false)
    private String name;
}

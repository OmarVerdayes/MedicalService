package utez.edu.mx.MedicalService.models.comments;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.MedicalService.models.users.Users;

@Entity
@Table(name="commment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( columnDefinition = "text",nullable = false)
    private String comment;
    @Column( columnDefinition = "int(1)",nullable = false)
    private int qualification;
    @ManyToOne
    @JoinColumn(name = "id_doctor",nullable = false)
    private Users doctor;
}

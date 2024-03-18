package utez.edu.mx.MedicalService.models.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.MedicalService.models.users.Users;

@Entity
@Table(name="patient")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( columnDefinition = "varchar(11)",nullable = false)
    private String nss;
    @Column( columnDefinition = "text",nullable = false)
    private String allergies;
    @Column( columnDefinition = "varchar(18)",nullable = false)
    private String account_number;

    @ManyToOne
    @JoinColumn(name = "id_user",nullable = false)
    private Users user;
}

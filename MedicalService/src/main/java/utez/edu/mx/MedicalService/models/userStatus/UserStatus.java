package utez.edu.mx.MedicalService.models.userStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="userstatus")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( columnDefinition = "varchar(255)",nullable = false)
    private String name;
}

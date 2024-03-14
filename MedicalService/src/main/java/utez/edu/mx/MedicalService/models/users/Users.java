package utez.edu.mx.MedicalService.models.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.MedicalService.models.roles.Roles;
import utez.edu.mx.MedicalService.models.userStatus.UserStatus;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( columnDefinition = "varchar(90)",nullable = false)
    private String email;
    @Column( columnDefinition = "text",nullable = false)
    private String password;
    @Column( columnDefinition = "varchar(60)",nullable = false)
    private String name;
    @Column( columnDefinition = "varchar(45)",nullable = false)
    private String fisrt_surname;
    @Column( columnDefinition = "varchar(45)")
    private String second_surname;
    @Column( columnDefinition = "varchar(10)",nullable = false)
    private String phone;
    @ManyToOne
    @JoinColumn(name = "id_rol",nullable = false)
    private Roles rol;
    @ManyToOne
    @JoinColumn(name = "id_status",nullable = false)
    private UserStatus status;
}

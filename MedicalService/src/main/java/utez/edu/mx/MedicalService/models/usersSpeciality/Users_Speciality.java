package utez.edu.mx.MedicalService.models.usersSpeciality;

import jakarta.persistence.*;
import lombok.*;
import utez.edu.mx.MedicalService.models.speciality.Speciality;
import utez.edu.mx.MedicalService.models.users.Users;
@Entity
@Table(name = "users_speciality")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Users_Speciality {

    @EmbeddedId
    private Users_Speciality_Id id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "usuario_id")
    private Users user;

    @ManyToOne
    @MapsId("specialityId")
    @JoinColumn(name = "especialidad_id")
    private Speciality speciality;
}

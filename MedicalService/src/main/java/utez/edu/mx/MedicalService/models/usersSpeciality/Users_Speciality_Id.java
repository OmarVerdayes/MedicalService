package utez.edu.mx.MedicalService.models.usersSpeciality;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Users_Speciality_Id implements Serializable {
    private Long userId;
    private Long specialityId;

}

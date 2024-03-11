package utez.edu.mx.MedicalService.controllers.users;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.MedicalService.models.roles.Roles;
import utez.edu.mx.MedicalService.models.speciality.Speciality;
import utez.edu.mx.MedicalService.models.userStatus.UserStatus;
import utez.edu.mx.MedicalService.models.users.Users;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsersDTO {
    private Long id;
    @NotNull(message="El nombre es obligatorio")
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 60, message = "El comentario debe tener entre 3 y 60 caracteres")
    private String name;

    @NotNull(message="El nombre es obligatorio")
    @NotBlank(message = "El nombre es obligatorio")
    private String password;

    @NotNull(message="El email es obligatorio")
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El correo electrónico debe tener un formato válido")
    @Size(min = 3, max = 90, message = "El comentario debe tener entre 3 y 90 caracteres")
    private String email;

    @NotNull(message="El primer apellido apellido es obligatorio")
    @NotBlank(message = "El primer apellido apellido es obligatorio")
    @Size(min = 3, max = 45, message = "El primer apellido debe tener entre 3 y 45 caracteres")
    private String fisrt_surname;

    @Size(min = 3, max = 45, message = "El segundo apellido debe tener entre 3 y 45 caracteres")
    private String second_surname;

    @NotNull(message="El Telefono es obligatorio")
    @NotBlank(message = "El Telefono es obligatorio")
    @Size(min = 10, max = 10, message = "El Telefono debe tener 10 caracteres")
    private String phone;

    private Roles rol;
    private UserStatus status;

    public Users castToOriginalObject(){
        return new Users(id,email,password, name, fisrt_surname, second_surname,phone,rol,status);
    }
}

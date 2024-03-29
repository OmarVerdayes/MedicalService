package utez.edu.mx.MedicalService.controllers.users.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import utez.edu.mx.MedicalService.models.roles.Roles;
import utez.edu.mx.MedicalService.models.userStatus.UserStatus;
import utez.edu.mx.MedicalService.models.users.Users;
import utez.edu.mx.MedicalService.utils.ActionsFiles;

import java.io.IOException;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsersDTO {
    @Value("${app.url.route.users.image}")
    private String urlRouteImage;
    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    ActionsFiles actionsFiles=new ActionsFiles();

    private Long id;
    @NotNull(message="El nombre es obligatorio")
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 60, message = "El nombre debe tener entre 3 y 60 caracteres")
    private String name;

    @NotNull(message="El password es obligatorio")
    @NotBlank(message = "El password es obligatorio")
    @Size(min = 3, message = "El la contraseña  debe tener minimo 3 caracteres")
    private String password;

    @NotNull(message="El email es obligatorio")
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El correo electrónico debe tener un formato válido")
    @Size(min = 3, max = 90, message = "El email debe tener entre 3 y 90 caracteres")
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

    private MultipartFile imageFile;

    private Roles rol;
    private UserStatus status;

    public Users castToOriginalObject() throws IOException {
        return new Users(id,email,bCryptPasswordEncoder.encode(password), name, fisrt_surname, second_surname,phone,actionsFiles.saveFile(imageFile,urlRouteImage),rol,status);
    }
    public Users castToOriginalObjectNoImage(String oldImage) throws IOException {
        return new Users(id,email,password, name, fisrt_surname, second_surname,phone,oldImage,rol,status);
    }



}

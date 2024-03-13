package utez.edu.mx.MedicalService.controllers.commments.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;
import utez.edu.mx.MedicalService.models.comments.Comments;
import utez.edu.mx.MedicalService.models.users.Users;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentsDTO {

    private Long id;
    @NotNull(message="El comentario es obligatorio")
    @NotEmpty(message = "El comentario es obligatorio")
    @Size(min = 3, max = 300, message = "El comentario debe tener entre 3 y 300 caracteres")
    private String comment;
    @NotNull(message = "La calificación es obligatoria")
    @Digits(integer = 1, fraction = 0, message = "La calificación debe ser un número entero")
    @Min(value = 1, message = "La calificación debe ser igual o mayor que 1")
    @Max(value = 5, message = "La calificación debe ser igual o menor que 5")
    private int qualification;
    private Users users;

    public Comments castToOriginalObject(){
        return new Comments(id,comment,qualification,users);
    }

}

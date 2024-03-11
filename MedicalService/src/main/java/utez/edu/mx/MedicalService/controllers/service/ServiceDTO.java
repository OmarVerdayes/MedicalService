package utez.edu.mx.MedicalService.controllers.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import utez.edu.mx.MedicalService.models.service.ServiceM;
import utez.edu.mx.MedicalService.models.speciality.Speciality;
import utez.edu.mx.MedicalService.utils.ActionsFiles;

import java.io.IOException;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServiceDTO {
    @Value("${app.url.route.image}")
    private String urlRouteImage;

    ActionsFiles actionsFiles=new ActionsFiles();

    private Long id;
    @NotNull(message="El titulo es obligatorio")
    @NotBlank(message = "El titulo es obligatorio")
    @Size(min = 3, max = 60, message = "El titulo debe tener entre 3 y 60 caracteres")
    private String title;


    @NotNull(message="El resumen es obligatorio")
    @NotBlank(message = "El resumen es obligatorio")
    @Size(min = 3, max = 255, message = "El resumen debe tener entre 3 y 255 caracteres")
    private String summary;

    @NotNull(message="La descripcion es description")
    @NotBlank(message = "La descripcion es description")
    @Size(min = 3, message = "La descripcion debe tener entre minimo 3 caracteres")
    private String description;

    @NotNull(message="La fecha de inicio es obligatoria")
    @NotBlank(message = "La fecha de inicio es obligatoria")
    @Size(min = 3, message = "El comentario debe tener minimo 3")
    private MultipartFile imageArchivo;

    private Speciality speciality;

    public ServiceM castToOriginalObject() throws IOException {
        return new ServiceM(id,title, summary, description, actionsFiles.saveFile(imageArchivo,urlRouteImage), speciality);
    }

}

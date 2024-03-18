package utez.edu.mx.MedicalService.controllers.service.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import utez.edu.mx.MedicalService.models.areas.Areas;
import utez.edu.mx.MedicalService.models.service.ServiceM;
import utez.edu.mx.MedicalService.utils.ActionsFiles;
import utez.edu.mx.MedicalService.utils.validations.EndTimeAfterStartTime;

import java.io.IOException;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EndTimeAfterStartTime
public class ServiceDTO {
    @Value("${app.url.route.service.image}")
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
    @NotNull(message="El precio es obligatorio")
    @NotBlank(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    private Long price;

    private MultipartFile imageFile;

    private Areas areas;

    public ServiceM castToOriginalObject() throws IOException {
        return new ServiceM(id,title, summary, description,price, actionsFiles.saveFile(imageFile,urlRouteImage), areas);
    }
    public ServiceM castToOriginalObjectNoImage(String oldImage) throws IOException {
        return new ServiceM(id,title, summary, description,price, oldImage, areas);
    }

}

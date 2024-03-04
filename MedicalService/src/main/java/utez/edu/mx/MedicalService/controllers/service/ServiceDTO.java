package utez.edu.mx.MedicalService.controllers.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.MedicalService.models.service.ServiceM;
import utez.edu.mx.MedicalService.models.speciality.Speciality;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServiceDTO {
    private Long id;
    @NotNull(message="El titulo es obligatorio")
    @NotBlank(message = "El titulo es obligatorio")
    @Size(min = 3, max = 60, message = "El titulo debe tener entre 3 y 60 caracteres")
    private String title;


    @NotNull(message="El resumen es obligatorio")
    @NotBlank(message = "El resumen es obligatorio")
    @Size(min = 3, max = 255, message = "El resumen debe tener entre 3 y 255 caracteres")
    private String summary;

    @NotNull(message="El resumen es description")
    @NotBlank(message = "El resumen es description")
    @Size(min = 3,max = 255, message = "El comentario debe tener entre 3 y 255 caracteres")
    private String description;

    @NotNull(message="La fecha de inicio es obligatoria")
    @NotBlank(message = "La fecha de inicio es obligatoria")
    @Size(min = 3, message = "El comentario debe tener minimo 3")
    private String image;

    private Speciality speciality;

    public ServiceM castToOriginalObject(){
        return new ServiceM(id,title, summary, description, image, speciality);
    }

}

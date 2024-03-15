package utez.edu.mx.MedicalService.controllers.areas.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.MedicalService.models.areas.Areas;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AreasDTO {
    private Long id;

    @NotBlank(message = "La nombre del area es obligatoria")
    @Size(min = 3, max = 60, message = "El nombre del area debe tener entre 3 y 60 caracteres")
    private String name;

    public Areas castToOriginalObject(){
        return new Areas(id,name);
    }
}

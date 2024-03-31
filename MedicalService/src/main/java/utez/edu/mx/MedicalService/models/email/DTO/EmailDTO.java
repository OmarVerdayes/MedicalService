package utez.edu.mx.MedicalService.models.email.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmailDTO {
    private String title;
    private String emailUser;
    private String statusDescription;
    private String date;
    private String speciality;
}

package utez.edu.mx.MedicalService.controllers.service_purchase_history.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import utez.edu.mx.MedicalService.models.patient.Patient;
import utez.edu.mx.MedicalService.models.service.ServiceM;
import utez.edu.mx.MedicalService.models.service_purchase_history.Service_purchase_history;

import java.time.LocalDateTime;

@Data
public class Service_purchase_historyDTO {
    private Long id;
    @NotNull(message="El precio es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    private Long price;

    private ServiceM service;
    private Patient patient;

    public Service_purchase_history castToOriginalObject(){
        return new Service_purchase_history(id,price, LocalDateTime.now().toString(),service,patient);
    }

}

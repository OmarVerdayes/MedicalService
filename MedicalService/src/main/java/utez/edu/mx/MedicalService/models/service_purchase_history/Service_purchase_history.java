package utez.edu.mx.MedicalService.models.service_purchase_history;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.MedicalService.models.patient.Patient;
import utez.edu.mx.MedicalService.models.service.ServiceM;

@Entity
@Table(name="service_purchase_history")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Service_purchase_history {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( columnDefinition = "DECIMAL",nullable = false)
    private Long price;
    @Column( columnDefinition = "DateTime",nullable = false)
    private String buy_date;
    @ManyToOne
    @JoinColumn(name = "id_service",nullable = false)
    private ServiceM service;
    @ManyToOne
    @JoinColumn(name = "id_patient",nullable = false)
    private Patient patient;
}

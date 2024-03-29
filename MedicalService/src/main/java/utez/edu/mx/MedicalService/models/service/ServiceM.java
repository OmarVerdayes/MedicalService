package utez.edu.mx.MedicalService.models.service;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.MedicalService.models.areas.Areas;

@Entity
@Table(name="service")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServiceM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( columnDefinition = "varchar(60)",nullable = false)
    private String title;
    @Column( columnDefinition = "varchar(255)",nullable = false)
    private String summary;
    @Column( columnDefinition = "text",nullable = false)
    private String description;
    @Column( columnDefinition = "DECIMAL",nullable = false)
    private Long price;
    @Column( columnDefinition = "text",nullable = false)
    private String image;
    @ManyToOne
    @JoinColumn(name = "id_area",nullable = false)
    private Areas areas;
}

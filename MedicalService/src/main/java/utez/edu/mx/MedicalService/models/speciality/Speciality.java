package utez.edu.mx.MedicalService.models.speciality;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.MedicalService.models.areas.Areas;

@Entity
@Table(name="speciality")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( columnDefinition = "varchar(60)",nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_area",nullable = false)
    private Areas area;
}

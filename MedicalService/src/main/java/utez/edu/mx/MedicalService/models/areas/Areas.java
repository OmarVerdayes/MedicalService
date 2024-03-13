package utez.edu.mx.MedicalService.models.areas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="area")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Areas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( columnDefinition = "varchar(90)",nullable = false)
    private String name;
}



package utez.edu.mx.MedicalService.models.binnacle;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name="binnacle")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Binnacle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( columnDefinition = "varchar(255)",nullable = false)
    private String action_name;
    @Column( columnDefinition = "varchar(255)",nullable = false)
    private String table_name;
    @Column( columnDefinition = "longtext")
    private String old_values;
    @Column( columnDefinition = "longtext")
    private String new_values;
    @Column( columnDefinition = "DATETIME",nullable = false)
    private Date date_action;
}

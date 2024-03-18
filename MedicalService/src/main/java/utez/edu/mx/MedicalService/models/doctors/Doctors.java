package utez.edu.mx.MedicalService.models.doctors;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.MedicalService.models.users.Users;

import java.sql.Time;

@Entity
@Table(name="doctors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Doctors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( columnDefinition = "varchar(20)",nullable = false)
    private String id_number; //esto es el numero de cedula
    @Column( columnDefinition = "int(3)",nullable = false)
    private Long consulting_room;

    @Column( columnDefinition = "Time",nullable = false)
    private Time shift_start_time;
    @Column( columnDefinition = "Time",nullable = false)
    private Time shift_finish_time;

    @ManyToOne
    @JoinColumn(name = "id_user",nullable = false)
    private Users user;

 }

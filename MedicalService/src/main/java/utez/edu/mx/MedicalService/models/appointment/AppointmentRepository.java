package utez.edu.mx.MedicalService.models.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AppointmentRepository  extends JpaRepository<Appointment,Long> {
    @Modifying
    @Query("UPDATE Appointment AS A SET A.appointmentStatus = (SELECT status FROM AppointmentStatus AS status WHERE status.name = 'Denegada') WHERE A.id = :id_appointment")
    void  updateStatusToDenied (@Param("id_appointment") Long id_appointment);
    @Modifying
    @Query("UPDATE Appointment AS A SET A.appointmentStatus = (SELECT status FROM AppointmentStatus AS status WHERE status.name = 'Aceptada') WHERE A.id = :id_appointment")
    void  updateStatusToAccepted(@Param("id_appointment") Long id_appointment);

    @Modifying
    @Query("UPDATE Appointment AS A SET A.appointmentStatus = (SELECT status FROM AppointmentStatus AS status WHERE status.name = 'Cancelada') WHERE A.id = :id_appointment")
    void  updateStatusToCancelled(@Param("id_appointment") Long id_appointment);
    @Modifying
    @Query("UPDATE Appointment AS A SET A.appointmentStatus = (SELECT status FROM AppointmentStatus AS status WHERE status.name = 'Finalizada') WHERE A.id = :id_appointment")
    void  updateStatusToFinished(@Param("id_appointment") Long id_appointment);
    @Query("select Appo from Appointment  as Appo where Appo.doctor.id=:id_doctor and Appo.appointmentStatus.name='Finalizada'")
    List<Appointment> getFinishAppointmentDoctor(@Param("id_doctor")Long id_doctor);

    @Query("select Appo from Appointment as Appo where Appo.doctor.id=:id_doctor and Appo.appointmentStatus.name='Cancelada'")
    List<Appointment> getCancelledAppointmentDoctor(@Param("id_doctor")Long id_doctor);

    @Query("select Appo from Appointment as Appo where Appo.patient.id=:id_patient and Appo.appointmentStatus.name=:status")
    List<Appointment> getAppointmentPatientByStatus(@Param("id_patient")Long id_patient,@Param("status")String status);

    @Query("select appo.patient.user.email from Appointment As appo where appo.id=:id_appointment")
    String getEmailOfPatient (@Param("id_appointment")Long id_appointment);

    @Query("select appo.start_date from Appointment As appo where appo.id=:id_appointment")
    String getDateAppointment (@Param("id_appointment")Long id_appointment);
    @Query("select appo.speciality.name from Appointment As appo where appo.id=:id_appointment")
    String getAppointmentSpeciality (@Param("id_appointment")Long id_appointment);

    @Query("SELECT CASE WHEN a.appointmentStatus.name =:name_status THEN true ELSE false END FROM Appointment a WHERE a.id = :id_appointment")
    boolean statusAppointmentIs_Name(@Param("id_appointment")Long id_appointment,@Param("name_status")String name_status);

}

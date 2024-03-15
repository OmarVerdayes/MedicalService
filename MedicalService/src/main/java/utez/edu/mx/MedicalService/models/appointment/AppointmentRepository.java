package utez.edu.mx.MedicalService.models.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AppointmentRepository  extends JpaRepository<Appointment,Long> {
    @Modifying
    @Query("update Appointment As A set A.appointmentStatus.id=:id_new_status where A.id=:id_appointment")
    void  updateStatusById (@Param("id_appointment") Long id_appointment,@Param("id_new_status") Long id_new_status);



}

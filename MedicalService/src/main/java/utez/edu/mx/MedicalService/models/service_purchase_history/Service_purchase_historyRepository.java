package utez.edu.mx.MedicalService.models.service_purchase_history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Service_purchase_historyRepository extends JpaRepository<Service_purchase_history,Long> {

    @Query("select his from Service_purchase_history As his where his.patient.user.email=:email")
    List<Service_purchase_history> getUserHistory(@Param("email") String email);


}

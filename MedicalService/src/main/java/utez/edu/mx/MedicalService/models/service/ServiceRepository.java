package utez.edu.mx.MedicalService.models.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceRepository extends JpaRepository<ServiceM,Long> {

    @Query("select ser.image from ServiceM AS ser where ser.id=:id")
    String getOldImage (@Param("id")Long id);

}

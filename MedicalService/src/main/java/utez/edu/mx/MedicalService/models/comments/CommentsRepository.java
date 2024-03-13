package utez.edu.mx.MedicalService.models.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentsRepository extends JpaRepository<Comments,Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Comments AS c WHERE C.doctor.id=:id_doctor and C.doctor.rol.name='Doctor'")
    boolean isDoctor (@Param("id_doctor") Long id_doctor);


}

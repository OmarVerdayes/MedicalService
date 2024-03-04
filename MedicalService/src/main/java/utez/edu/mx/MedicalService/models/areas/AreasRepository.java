package utez.edu.mx.MedicalService.models.areas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AreasRepository extends JpaRepository<Areas,Long> {

     boolean existsByName (String name);

}

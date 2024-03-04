package utez.edu.mx.MedicalService.models.roles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles,Long> {


     boolean existsByName(String name);
}

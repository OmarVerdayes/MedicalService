package utez.edu.mx.MedicalService.models.userStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatusRepository extends JpaRepository<UserStatus,Long> {
    boolean existsByName(String name);
}

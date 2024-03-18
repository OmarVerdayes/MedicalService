package utez.edu.mx.MedicalService.models.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {

     boolean existsByEmail(String email);

    Optional<Users> getOneByEmail(String email);


    @Query("select user.image from Users  as user where user.id=:id")
    String getOldImage(@Param("id")Long id);
}

package utez.edu.mx.MedicalService.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ScriptInitializer {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        if (checkIfTableIsEmpty("roles")) {
            jdbcTemplate.execute("INSERT INTO `roles` (`name`) VALUES ('Administrador'),('Doctor'),('Paciente');");
        }

        if (checkIfTableIsEmpty("user_status")) {
            jdbcTemplate.execute("INSERT INTO `user_status` (`name`) VALUES ('Activo'),('Inactivo');");
        }

        if (checkIfTableIsEmpty("users")) {
            jdbcTemplate.execute("INSERT INTO `users` (`email`, `fisrt_surname`, `name`, `password`, `phone`, `second_surname`,image, `id_rol`, `id_status`) VALUES " +
                    "('20213tn042@utez.edu.mx', 'Santander', 'Omar de Jesus', '$2a$10$guSF3YkYTLX2JHjmXjH0K.sVzZxAv1GTFDg8jWTN2xERBJUdNAlPO', '7775196369', 'Verdayes','imagen1','1', '1')," +
                    "('20213tn043@utez.edu.mx', 'Santana', 'mendoza', '$2a$10$Hv8DF3vP5h/IhyU.TTMpDuVB8TZ4Q5TpY2dgkrMdlguIEG.6LCn3e', '7775196369', 'Mendoza','imagen1', '2', '1')," +
                    "('20213tn044@utez.edu.mx', 'Diaz', 'Andrea Elizabeth', '$2a$10$IZRalDZ82KkgwkYXXmlCyOhvjkDgglys.DzN5eikBYOdp5ieGK7bq', '7775196369', 'Zagal','imagen1', '3', '1');");
        }

    }
    private boolean checkIfTableIsEmpty(String tableName) {
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + tableName, Integer.class);
        return count == 0;
    }
}

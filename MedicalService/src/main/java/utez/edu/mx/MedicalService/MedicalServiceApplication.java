package utez.edu.mx.MedicalService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class MedicalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalServiceApplication.class, args);
	}

}


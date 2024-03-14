package utez.edu.mx.MedicalService.controllers.email;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.MedicalService.controllers.email.DTO.EmailDTO;
import utez.edu.mx.MedicalService.services.email.EmailService;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = {"*"})
public class EmailController {
    @Autowired
    EmailService service;

    @PostMapping("/")
    public ResponseEntity<Object> sendEmail(@Validated @RequestBody EmailDTO emailDTO) throws MessagingException {
        service.sendEmail(emailDTO);

        return ResponseEntity.ok().body("Email enviado");
    }
}

package utez.edu.mx.MedicalService.services.email;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.MedicalService.controllers.email.DTO.EmailDTO;
import utez.edu.mx.MedicalService.models.email.MailManeger;

@Service
public class EmailService {
    @Autowired
    MailManeger mailManeger;


    public void sendEmail (EmailDTO emailDTO) throws MessagingException {
        mailManeger.sendMessge(emailDTO);
    }

}

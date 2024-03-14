package utez.edu.mx.MedicalService.models.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import utez.edu.mx.MedicalService.controllers.email.DTO.EmailDTO;

@Component
public class MailManeger {

    @Value("${spring.mail.username}")
    private String sender;

    JavaMailSender javaMailSender;

    public MailManeger(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public  void sendMessge(EmailDTO emailDTO) throws MessagingException {
        MimeMessage message=javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        helper.setTo(emailDTO.getEmailUser());
        helper.setText(emailDTO.getMessage());
        helper.setFrom(sender);
        javaMailSender.send(message);

        try{
            message.setSubject("Prueba de correo");
        }catch (MessagingException e){
            throw new RuntimeException(e);
        }


    }


}

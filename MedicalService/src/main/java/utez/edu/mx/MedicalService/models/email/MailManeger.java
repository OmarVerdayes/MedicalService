package utez.edu.mx.MedicalService.models.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import utez.edu.mx.MedicalService.controllers.email.DTO.EmailDTO;

import java.io.File;

@Component
public class MailManeger {

    @Value("${spring.mail.username}")
    private String sender;

    JavaMailSender javaMailSender;

    public MailManeger(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public  void sendMessge(EmailDTO emailDTO) {
        MimeMessage message=javaMailSender.createMimeMessage();

        try{
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(sender);
            helper.setTo(emailDTO.getEmailUser());
            helper.setSubject("Confirmacion de cita");


            String htmlMsg = "<html><body>"
                    + "<header style='background-color: #007bff; color: white; padding: 10px; text-align: center;'>"
                    + "<h1>Confirmacion de cita</h1>"
                    + "</header>"
                    + "<div style='padding: 20px;'>"
                    //+ "<h2 style='color: #007bff;'>Título del Correo</h2>"
                    + "<p style='font-size: 16px;'>Este es un correo de prueba con estilo HTML.</p>"
                    + "<img src='cid:image' alt='Pie de imagen' style='width: 100%;'>"
                    //+ "<p>Detalles adicionales: " + emailDTO.getMessage() + "</p>"
                    + "</div>"
                    + "<footer style='background-color: #007bff; color: white; padding: 5px; text-align: center;'>"
                    + "<p>Este es un mensaje de correo electrónico automático. Por favor no responda.</p>"
                    + "</footer>"
                    + "</body></html>";
            helper.setText(htmlMsg, true);
            javaMailSender.send(message);
        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }


}

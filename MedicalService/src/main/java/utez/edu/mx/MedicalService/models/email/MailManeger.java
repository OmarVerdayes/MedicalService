package utez.edu.mx.MedicalService.models.email;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import utez.edu.mx.MedicalService.models.email.DTO.EmailDTO;

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
            helper.setSubject("Cita medica "+emailDTO.getStatusDescription());


            String htmlMsg = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "  <head>\n" +
                    "    <meta charset=\"UTF-8\" />\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                    "    <title>Cita medica "+emailDTO.getTitle()+"</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"style.css\" />\n" +
                    "    <style>\n" +
                    "      *,\n" +
                    "*::before,\n" +
                    "*::after {\n" +
                    "  box-sizing: border-box;\n" +
                    "  margin: 0;\n" +
                    "  padding: 0;\n" +
                    "}\n" +
                    "\n" +
                    "body {\n" +
                    "  max-width: 1200px;\n" +
                    "  margin: 0 auto;\n" +
                    "  font-family: \"Roboto\", sans-serif;\n" +
                    "  padding: 0 20px;\n" +
                    "}\n" +
                    "\n" +
                    "figure {\n" +
                    "  display: flex;\n" +
                    "}\n" +
                    "\n" +
                    "/* Empezamos con el banner  */\n" +
                    "\n" +
                    ".banner {\n" +
                    "  text-align: center;\n" +
                    "  padding-top: 3rem;\n" +
                    "}\n" +
                    "\n" +
                    ".banner__container {\n" +
                    "  display: flex;\n" +
                    "  flex-direction: column;\n" +
                    "  align-items: center;\n" +
                    "  padding: 30px 30px 0;\n" +
                    "}\n" +
                    "\n" +
                    ".banner__bg {\n" +
                    "  border-radius: 10px;\n" +
                    "  background-color:  skyblue;\n" +
                    "  margin-top: 3rem;\n" +
                    "}\n" +
                    "\n" +
                    ".banner__title {\n" +
                    "  font-size: 32px;\n" +
                    "  font-weight: 700;\n" +
                    "  margin-bottom: 12px;\n" +
                    "}\n" +
                    "\n" +
                    ".banner__description {\n" +
                    "  font-size: 18px;\n" +
                    "  max-width: 700px;\n" +
                    "  margin-left: auto;\n" +
                    "  margin-right: auto;\n" +
                    "  margin-bottom: 12px;\n" +
                    "}\n" +
                    "\n" +
                    ".banner__title,\n" +
                    ".banner__description {\n" +
                    "  color: white;\n" +
                    "}\n" +
                    "\n" +
                    ".banner__img {\n" +
                    "  width: 250px;\n" +
                    "}\n" +
                    "\n" +
                    "/* Btn  */\n" +
                    ".button {\n" +
                    "  text-decoration: none;\n" +
                    "  text-transform: uppercase;\n" +
                    "  border: none;\n" +
                    "  outline: none;\n" +
                    "  background-color: white;\n" +
                    "  padding: 10px 15px;\n" +
                    "  display: flex;\n" +
                    "  max-width: fit-content;\n" +
                    "  margin: 0 auto;\n" +
                    "  color: #292929;\n" +
                    "  font-size: 12px;\n" +
                    "  transition: background-color 0.3s ease;\n" +
                    "  cursor: pointer;\n" +
                    "}\n" +
                    "\n" +
                    ".button:hover,\n" +
                    ".button:focus {\n" +
                    "  background-color: #e7e6e6;\n" +
                    "}\n" +
                    "\n" +
                    "@media (min-width: 768px) {\n" +
                    "  .banner {\n" +
                    "    text-align: left;\n" +
                    "  }\n" +
                    "\n" +
                    "  .banner__container {\n" +
                    "    flex-direction: row;\n" +
                    "    justify-content: space-around;\n" +
                    "    padding: 0 30px;\n" +
                    "  }\n" +
                    "\n" +
                    "  .button {\n" +
                    "    margin-right: auto;\n" +
                    "    margin-left: 0;\n" +
                    "  }\n" +
                    "}\n" +
                    "\n" +
                    "    </style>\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <div class=\"banner\">\n" +
                    "      <div class=\"banner__bg\">\n" +
                    "        <div class=\"banner__container\">\n" +
                    "          <div class=\"banner__data\">\n" +
                    "            <h2 class=\"banner__title\">¡"+emailDTO.getTitle()+"!</h2>\n" +
                    "            <p class=\"banner__description\">\n" +
                                    "Se le informa que su cita para la especialidad "+emailDTO.getSpeciality()+
                                    " agendada en la fecha "+emailDTO.getDate()+
                                    " ha sido "+ emailDTO.getStatusDescription() +"\n"+
                    "               Favor de no responder este correo.\n" +
                    "            </p>\n" +
                    "            <a href=\"#contact\" class=\"button\">Ir al sitio</a>\n" +
                    "          </div>\n" + "\n" +
                    "          <figure>\n" +
                    "            <img\n" +
                    "              class=\"banner__img\"\n" +
                    "              src=\"https://www.freepnglogos.com/uploads/medicine-logo-png-1.png\"\n" +
                    "              alt=\"banner\"\n" +
                    "              width=\"250\"\n" +
                    "              height=\"250\"\n" +
                    "            />\n" +
                    "          </figure>\n" +
                    "\n" +
                    "\n" +
                    "        \n" +
                    "        </div>\n" +
                    "      </div>\n" +
                    "    </div>\n" +
                    "  </body>\n" +
                    "</html>\n";

            
            helper.setText(htmlMsg, true);
            javaMailSender.send(message);
        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }


}

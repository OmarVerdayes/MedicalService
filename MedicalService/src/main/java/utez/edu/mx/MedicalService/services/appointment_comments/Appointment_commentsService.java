package utez.edu.mx.MedicalService.services.appointment_comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import utez.edu.mx.MedicalService.controllers.appointment_comments.DTO.Appointment_commentsDTO;
import utez.edu.mx.MedicalService.models.appointment_comments.Appointment_comments;
import utez.edu.mx.MedicalService.models.appointment_comments.Appointment_commentsRepository;
import utez.edu.mx.MedicalService.utils.ConvertErrorsValidationToString;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
public class Appointment_commentsService{

    @Autowired
    Appointment_commentsRepository repository;

    ConvertErrorsValidationToString convertErrors=new ConvertErrorsValidationToString();

    @Transactional(readOnly=true)
    public CustomResponse<List<Appointment_comments>> getAll(){

        try {
            return new CustomResponse<>(this.repository.findAll(),false,200, "OK");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al obtener los comentarios");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Appointment_comments> insert(Appointment_commentsDTO appointmentCommentsDTO, BindingResult bindingResult){
        try {

            if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }

            return new CustomResponse<>(this.repository.saveAndFlush(appointmentCommentsDTO.castToOriginalObject()), false,200,"Comentario registrado");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al registrar el comentario");
        }
    }

    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<Appointment_comments> update(Appointment_commentsDTO appointmentCommentsDTO, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }else if(!this.repository.existsById(appointmentCommentsDTO.getId())){
                return new CustomResponse<>(null,true,400,"El comentario no existe");
            }

            return new CustomResponse<>(this.repository.saveAndFlush(appointmentCommentsDTO.castToOriginalObject()),false,200,"Comentario actualizado actualizada");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al actualizar el comentario");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Appointment_comments> delete(Long id){
        try {
            if(!this.repository.existsById(id))
                return new CustomResponse<>(null,true,400,"El Comentario no existe");

            this.repository.deleteById(id);
            return new CustomResponse<>(null,false,200,"Comentario eliminado coreectamente");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al eliminar el comentario");
        }
    }

}

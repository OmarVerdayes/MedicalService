package utez.edu.mx.MedicalService.services.doctor_comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import utez.edu.mx.MedicalService.controllers.doctor_comments.DTO.Doctor_commentsDTO;
import utez.edu.mx.MedicalService.models.doctor_comments.Doctor_comments;
import utez.edu.mx.MedicalService.models.doctor_comments.Doctor_commentsRepository;
import utez.edu.mx.MedicalService.utils.ConvertErrorsValidationToString;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
public class Doctor_commentsService {

    @Autowired
    Doctor_commentsRepository repository;

    ConvertErrorsValidationToString convertErrors=new ConvertErrorsValidationToString();

    @Transactional(readOnly=true)
    public CustomResponse<List<Doctor_comments>> getAll(){

        try {
            return new CustomResponse<>(this.repository.findAll(),false,200, "OK");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al obtener los comentarios");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Doctor_comments> insert(Doctor_commentsDTO doctorcommentsDTO, BindingResult bindingResult){
        try {

             if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
             }
            return new CustomResponse<>(this.repository.saveAndFlush(doctorcommentsDTO.castToOriginalObject()), false,200,"Comentario registrado");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al registrar el comentario");
        }
    }

    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<Doctor_comments> update(Doctor_commentsDTO doctorcommentsDTO, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }else if(!this.repository.existsById(doctorcommentsDTO.getId())){
                return new CustomResponse<>(null,true,400,"El comentario no existe");
            }

            return new CustomResponse<>(this.repository.saveAndFlush(doctorcommentsDTO.castToOriginalObject()),false,200,"Comentario actualizado actualizada");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al actualizar el comentario");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Doctor_comments> delete(Long id){
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

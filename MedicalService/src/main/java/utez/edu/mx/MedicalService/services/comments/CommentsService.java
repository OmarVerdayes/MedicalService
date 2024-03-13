package utez.edu.mx.MedicalService.services.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import utez.edu.mx.MedicalService.controllers.commments.DTO.CommentsDTO;
import utez.edu.mx.MedicalService.models.comments.Comments;
import utez.edu.mx.MedicalService.models.comments.CommentsRepository;
import utez.edu.mx.MedicalService.utils.ConvertErrorsValidationToString;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
public class CommentsService {

    @Autowired
    CommentsRepository repository;

    ConvertErrorsValidationToString convertErrors=new ConvertErrorsValidationToString();

    @Transactional(readOnly=true)
    public CustomResponse<List<Comments>> getAll(){

        try {
            return new CustomResponse<>(this.repository.findAll(),false,200, "OK");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al obtener los comentarios");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Comments> insert(CommentsDTO commentsDTO, BindingResult bindingResult){
        try {

             if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
             }else if(this.repository.isDoctor(commentsDTO.getUsers().getId())){
                 return new CustomResponse<>(null, true, 400, "Error el usuario al que desea agregar un comentario no es un doctor");
             }

            return new CustomResponse<>(this.repository.saveAndFlush(commentsDTO.castToOriginalObject()), false,200,"Comentario registrado");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al registrar el comentario");
        }
    }

    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<Comments> update(CommentsDTO commentsDTO, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }else if(!this.repository.existsById(commentsDTO.getId())){
                return new CustomResponse<>(null,true,400,"El comentario no existe");
            }

            return new CustomResponse<>(this.repository.saveAndFlush(commentsDTO.castToOriginalObject()),false,200,"Comentario actualizado actualizada");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al actualizar el comentario");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Comments> delete(Long id){
        try {
            if(!this.repository.existsById(id))
                return new CustomResponse<>(null,true,400,"El Comentario no existe");

            this.repository.deleteById(id);
            return new CustomResponse<>(null,false,200,"Comentario eliminado coreectamente");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al registrar el comentario");
        }
    }

}

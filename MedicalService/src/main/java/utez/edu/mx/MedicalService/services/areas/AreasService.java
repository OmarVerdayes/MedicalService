package utez.edu.mx.MedicalService.services.areas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import utez.edu.mx.MedicalService.controllers.areas.DTO.AreasDTO;
import utez.edu.mx.MedicalService.models.areas.Areas;
import utez.edu.mx.MedicalService.models.areas.AreasRepository;
import utez.edu.mx.MedicalService.utils.ConvertErrorsValidationToString;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service

public class AreasService {
    @Autowired
    AreasRepository repository;

    ConvertErrorsValidationToString convertErrors=new ConvertErrorsValidationToString();

    @Transactional(readOnly=true)
    public CustomResponse<List<Areas>> getAll(){

        try {
            return new CustomResponse<>(this.repository.findAll(),false,200, "OK");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al obtener las areas");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Areas> insert(AreasDTO area, BindingResult bindingResult){
        try {
            if(this.repository.existsByName(area.getName())){
                return new CustomResponse<>(null, false,200,"Ya existe una area registrado con ese nombre");
            }else if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }

            return new CustomResponse<>(this.repository.saveAndFlush(area.castToOriginalObject()), false,200,"Area registrado");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al registrar el area");
        }
    }

    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<Areas> update(AreasDTO areasDTO, BindingResult bindingResult){
        try {

            if(!this.repository.existsById(areasDTO.getId())){
                return new CustomResponse<>(null,true,400,"El area no existe");
            }else if(this.repository.existsByName(areasDTO.getName())){
                return new CustomResponse<>(null, false,200,"Ya existe una area registrado con ese nombre");
            }else if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }

            return new CustomResponse<>(this.repository.saveAndFlush(areasDTO.castToOriginalObject()),false,200,"Area actualizada");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al actualizar el area");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Areas> delete(Long id){
        try {
            if(!this.repository.existsById(id))
                return new CustomResponse<>(null,true,400,"El area no existe");

            this.repository.deleteById(id);
            return new CustomResponse<>(null,false,200,"Area eliminado coreectamente");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al eliminar el area");
        }
    }

}

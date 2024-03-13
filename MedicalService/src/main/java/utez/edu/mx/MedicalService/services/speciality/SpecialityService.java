package utez.edu.mx.MedicalService.services.speciality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import utez.edu.mx.MedicalService.controllers.speciality.DTO.SpecialityDTO;
import utez.edu.mx.MedicalService.models.speciality.Speciality;
import utez.edu.mx.MedicalService.models.speciality.SpecialityRepository;
import utez.edu.mx.MedicalService.utils.ConvertErrorsValidationToString;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
public class SpecialityService {

    @Autowired
    SpecialityRepository  repository;
    ConvertErrorsValidationToString convertErrors=new ConvertErrorsValidationToString();

    @Transactional(readOnly=true)
    public CustomResponse<List<Speciality>> getAll(){

        try {
            return new CustomResponse<>(this.repository.findAll(),false,200, "OK");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al obtener las especialidades");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Speciality> insert(SpecialityDTO specialityDTO, BindingResult bindingResult){
        try {
            if(this.repository.existsByName(specialityDTO.getName())){
                return new CustomResponse<>(null, false,200,"Ya existe una espcialidad registrado con ese nombre");
            }else if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }

            return new CustomResponse<>(this.repository.saveAndFlush(specialityDTO.castToOriginalObject()), false,200,"Especialidad registrada");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al registrar el especialidad");
        }
    }

    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<Speciality> update(SpecialityDTO specialityDTO, BindingResult bindingResult){
        try {

            if(!this.repository.existsById(specialityDTO.getId())){
                return new CustomResponse<>(null,true,400,"La especialidad no existe");
            }else if(this.repository.existsByName(specialityDTO.getName())){
                return new CustomResponse<>(null, false,200,"Ya existe una especialidad registrada con ese nombre");
            }else if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }

            return new CustomResponse<>(this.repository.saveAndFlush(specialityDTO.castToOriginalObject()),false,200,"Especialidad actualizada");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al actualizar la especialidad");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Speciality> delete(Long id){
        try {
            if(!this.repository.existsById(id))
                return new CustomResponse<>(null,true,400,"La especialidad no existe");

            this.repository.deleteById(id);
            return new CustomResponse<>(null,false,200,"La especialidad se elimino coreectamente");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al eliminar la especialidad");
        }
    }

}


package utez.edu.mx.MedicalService.services.docstors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import utez.edu.mx.MedicalService.controllers.doctors.DTO.DoctorDTO;
import utez.edu.mx.MedicalService.models.doctors.Doctors;
import utez.edu.mx.MedicalService.models.doctors.DoctorsRepository;
import utez.edu.mx.MedicalService.utils.ConvertErrorsValidationToString;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
public class DoctorsService {
    @Autowired
    DoctorsRepository repository;

    ConvertErrorsValidationToString convertErrors=new ConvertErrorsValidationToString();

    @Transactional(readOnly=true)
    public CustomResponse<List<Doctors>> getAll(){

        try {
            return new CustomResponse<>(this.repository.findAll(),false,200, "OK");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al obtener los doctores");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Doctors> insert(DoctorDTO doctorDTO, BindingResult bindingResult){
        try {
             if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }

            return new CustomResponse<>(this.repository.saveAndFlush(doctorDTO.castToOriginalObject()), false,200,"Doctor registrado");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al registrar el doctor");
        }
    }

    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<Doctors> update(DoctorDTO doctorDTO, BindingResult bindingResult){
        try {

            if(!this.repository.existsById(doctorDTO.getId())){
                return new CustomResponse<>(null,true,400,"El doctor no existe");
            }else if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }

            return new CustomResponse<>(this.repository.saveAndFlush(doctorDTO.castToOriginalObject()),false,200,"Doctor actualizado");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al actualizar el doctor");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Doctors> delete(Long id){
        try {
            if(!this.repository.existsById(id))
                return new CustomResponse<>(null,true,400,"El doctor no existe");

            this.repository.deleteById(id);
            return new CustomResponse<>(null,false,200,"Doctor eliminado coreectamente");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al eliminar el doctor");
        }
    }

}

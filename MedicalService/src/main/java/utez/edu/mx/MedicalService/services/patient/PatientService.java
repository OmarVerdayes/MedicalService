package utez.edu.mx.MedicalService.services.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import utez.edu.mx.MedicalService.controllers.patient.DTO.PatientDTO;
import utez.edu.mx.MedicalService.models.patient.Patient;
import utez.edu.mx.MedicalService.models.patient.PatientRepository;
import utez.edu.mx.MedicalService.utils.ConvertErrorsValidationToString;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientRepository repository;

    ConvertErrorsValidationToString convertErrors=new ConvertErrorsValidationToString();

    @Transactional(readOnly=true)
    public CustomResponse<List<Patient>> getAll(){

        try {
            return new CustomResponse<>(this.repository.findAll(),false,200, "OK");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al obtener los pacientes");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Patient> insert(PatientDTO patientDTO, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }


            return new CustomResponse<>(this.repository.saveAndFlush(patientDTO.castToOriginalObject()), false,200,"Paciente registrado");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al registrar al paciente");
        }
    }

    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<Patient> update(PatientDTO patientDTO, BindingResult bindingResult){
        try {

            if(!this.repository.existsById(patientDTO.getId())){
                return new CustomResponse<>(null,true,400,"El paciente no existe");
            }else if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }

            return new CustomResponse<>(this.repository.saveAndFlush(patientDTO.castToOriginalObject()),false,200,"Paciente se actualizo");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al actualizar el paciente");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Patient> delete(Long id){
        try {
            if(!this.repository.existsById(id))
                return new CustomResponse<>(null,true,400,"El paciente no existe");

            this.repository.deleteById(id);
            return new CustomResponse<>(null,false,200,"Paciente eliminado coreectamente");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al eliminar el paciente");
        }
    }

}



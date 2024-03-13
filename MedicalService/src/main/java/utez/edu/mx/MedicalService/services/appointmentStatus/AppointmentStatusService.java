package utez.edu.mx.MedicalService.services.appointmentStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import utez.edu.mx.MedicalService.controllers.appointmentStatus.DTO.AppointmentStatusDTO;
import utez.edu.mx.MedicalService.models.appointmentStatus.AppointmentStatus;
import utez.edu.mx.MedicalService.models.appointmentStatus.AppointmentStatusRepository;
import utez.edu.mx.MedicalService.utils.ConvertErrorsValidationToString;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
public class AppointmentStatusService {
    @Autowired
    AppointmentStatusRepository repository;

    ConvertErrorsValidationToString convertErrors=new ConvertErrorsValidationToString();


    @Transactional(readOnly=true)
    public CustomResponse<List<AppointmentStatus>> getAll(){
        try {
            return new CustomResponse<>(this.repository.findAll(),false,200, "OK");
        }catch (Exception e){
            return new CustomResponse<>(null,true,400, "Error al obtener los estatus de las citas");
        }
    }
    @Transactional(readOnly = true)
    public CustomResponse<AppointmentStatus> getOne(Long id){
        try {
            return new CustomResponse<>(this.repository.findById(id).get(),false,200,"OK");
        }catch (Exception e){
            return new CustomResponse<>(null,true,400, "Error al obtener los estatus de las citas");
        }

    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<AppointmentStatus> insert( AppointmentStatusDTO appointmentStatusDTO, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }
            return new CustomResponse<>(this.repository.saveAndFlush(appointmentStatusDTO.castToOriginalObject()), false,200,"Estatus de cita registrada!");
        }catch (Exception e){
            return new CustomResponse<>(null,true,400, "Error al registrar el status de la cita");
        }
    }
    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<AppointmentStatus> update(AppointmentStatusDTO appointmentStatusDTO, BindingResult bindingResult){
        try {
             if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }
            return new CustomResponse<>(this.repository.saveAndFlush(appointmentStatusDTO.castToOriginalObject()),false,200,"Estatus de la cita actualizada");
        }catch (Exception e){
            return new CustomResponse<>(null,true,400, "Error al actualizar el estatus de la cita");
        }
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<AppointmentStatus> delete(Long id){
        try {
            if(!this.repository.existsById(id))
                return new CustomResponse<>(null,true,400,"El estatus de la cita no existe");
            this.repository.deleteById(id);
            return new CustomResponse<>(null,false,200,"El estatus de la cita se elimino coreectamente");
        }catch (Exception e){
            return new CustomResponse<>(null,true,400, "Error al eliminar el estatus de la cita");
        }
    }
}
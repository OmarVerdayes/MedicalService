package utez.edu.mx.MedicalService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import utez.edu.mx.MedicalService.controllers.appointment.AppointmentDTO;
import utez.edu.mx.MedicalService.models.appointment.Appointment;
import utez.edu.mx.MedicalService.models.appointment.AppointmentRepository;
import utez.edu.mx.MedicalService.utils.ConvertErrorsValidationToString;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository repository;

    ConvertErrorsValidationToString convertErrors=new ConvertErrorsValidationToString();


    @Transactional(readOnly=true)
    public CustomResponse<List<Appointment>> getAll(){
        try {
            return new CustomResponse<>(this.repository.findAll(),false,200, "OK");
        }catch (Exception e){
            return new CustomResponse<>(null,true,400, "Error al obtener las citas");
        }
    }
    @Transactional(readOnly = true)
    public CustomResponse<Appointment> getOne(Long id){
        try {
            return new CustomResponse<>(this.repository.findById(id).get(),false,200,"OK");
        }catch (Exception e){
            return new CustomResponse<>(null,true,400, "Error al obtener la citas");
        }

    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Appointment> insert(@Validated AppointmentDTO appointmentDTO, BindingResult bindingResult){
        try {
            return new CustomResponse<>(this.repository.saveAndFlush(appointmentDTO.castToOriginalObject()), false,200,"Cita registrada!");
        }catch (Exception e){
            return new CustomResponse<>(null,true,400, "Error al registrar la cita");
        }
    }
    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<Appointment> update(AppointmentDTO appointmentDTO, BindingResult bindingResult){
        try {
            return new CustomResponse<>(this.repository.saveAndFlush(appointmentDTO.castToOriginalObject()),false,200,"Cita actualizada");
        }catch (Exception e){
            return new CustomResponse<>(null,true,400, "Error al actualizar la cita");
        }
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Appointment> delete(Long id){
        try {
            if(!this.repository.existsById(id))
                return new CustomResponse<>(null,true,400,"La cita no existe");
            this.repository.deleteById(id);
            return new CustomResponse<>(null,false,200,"La cita se elimino coreectamente");
        }catch (Exception e){
            return new CustomResponse<>(null,true,400, "Error al eliminar la cita");
        }
    }
}


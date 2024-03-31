package utez.edu.mx.MedicalService.services.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import utez.edu.mx.MedicalService.controllers.appointment.DTO.AppointmentDTO;
import utez.edu.mx.MedicalService.controllers.appointment.DTO.IdAppointmentDTO;
import utez.edu.mx.MedicalService.models.appointment.Appointment;
import utez.edu.mx.MedicalService.models.appointment.AppointmentRepository;
import utez.edu.mx.MedicalService.models.email.DTO.EmailDTO;
import utez.edu.mx.MedicalService.models.email.MailManeger;
import utez.edu.mx.MedicalService.utils.ConvertErrorsValidationToString;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository repository;
    @Autowired
    MailManeger mailManeger;
    ConvertErrorsValidationToString convertErrors=new ConvertErrorsValidationToString();
    @Transactional(readOnly=true)
    public CustomResponse<List<Appointment>> getAll(){
        try {
            return new CustomResponse<>(this.repository.findAll(),false,200, "OK");
        }catch (Exception e){
            return new CustomResponse<>(null,true,400, "Error al obtener las citas");
        }
    }
    @Transactional(readOnly=true)
    public CustomResponse<List<Appointment>> getFinishAppointmentDoctor(Long id_doctor){
        try {
            return new CustomResponse<>(this.repository.getFinishAppointmentDoctor(id_doctor),false,200, "OK");
        }catch (Exception e){
            return new CustomResponse<>(null,true,400, "Error al obtener el historial de citas del doctor");
        }
    }
    @Transactional(readOnly=true)
    public CustomResponse<List<Appointment>> getCancelledAppointmentDoctor(Long id_doctor){
        try {
            return new CustomResponse<>(this.repository.getCancelledAppointmentDoctor(id_doctor),false,200, "OK");
        }catch (Exception e){
            return new CustomResponse<>(null,true,400, "Error al obtener las canceladas del doctor");
        }
    }
    @Transactional(readOnly=true)
    public CustomResponse<List<Appointment>> getAppointmentPatientByStatus(Long id_patient,String status){
        try {
            return new CustomResponse<>(this.repository.getAppointmentPatientByStatus(id_patient,status),false,200, "OK");
        }catch (Exception e){
            return new CustomResponse<>(null,true,400, "Error al obtener las citas con el estatus: "+status);
        }
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Appointment> insert(@Validated AppointmentDTO appointmentDTO, BindingResult bindingResult){
        try {
             if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
             }
            return new CustomResponse<>(this.repository.saveAndFlush(appointmentDTO.castToOriginalObject()), false,200,"Cita registrada!");
        }catch (Exception e){
            return new CustomResponse<>(null,true,400, "Error al registrar la cita");
        }
    }
    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<Appointment> update(AppointmentDTO appointmentDTO, BindingResult bindingResult){
        try {
            if(!this.repository.existsById(appointmentDTO.getId())){
                return new CustomResponse<>(null,true,400,"La cita no existe");
            }else if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }
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
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Appointment> updateStatusToDenied(IdAppointmentDTO idAppointmentDTO, BindingResult bindingResult){
        try {
             if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }else if(!this.repository.existsById(idAppointmentDTO.getId_appointment())){
                return new CustomResponse<>(null,true,400,"La cita no existe");
            }else if(this.repository.statusAppointmentIs_Name(idAppointmentDTO.getId_appointment(),"Denegada")){
                 return new CustomResponse<>(null,true,400,"La cita ya esta con el estatus 'DENEGADA");
             }


            this.repository.updateStatusToDenied(idAppointmentDTO.getId_appointment());
            mailManeger.sendMessge(new EmailDTO("Cita denegada", repository.getEmailOfPatient(idAppointmentDTO.getId_appointment())
                    , "DENEGADA", repository.getDateAppointment(idAppointmentDTO.getId_appointment())
                    ,repository.getAppointmentSpeciality(idAppointmentDTO.getId_appointment())  ));
            return new CustomResponse<>(null,false,200,"El estatus de la cita se actualizo 'DENEGADA'");
        }catch (Exception e){
            return new CustomResponse<>(null,true,400, "Error al actualizar la cita");
        }
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Appointment> updateStatusToAccepted(IdAppointmentDTO idAppointmentDTO, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }if(!this.repository.existsById(idAppointmentDTO.getId_appointment())){
                return new CustomResponse<>(null,true,400,"La cita no existe");
            }else if(this.repository.statusAppointmentIs_Name(idAppointmentDTO.getId_appointment(),"Denegada")){
                return new CustomResponse<>(null,true,400,"La cita ya esta DENEGADA por lo que ya no se puede aceptar");
            }

            this.repository.updateStatusToAccepted(idAppointmentDTO.getId_appointment());
            mailManeger.sendMessge(new EmailDTO("Cita aceptada", repository.getEmailOfPatient(idAppointmentDTO.getId_appointment())
                    , "ACEPTADA", repository.getDateAppointment(idAppointmentDTO.getId_appointment())
                    ,repository.getAppointmentSpeciality(idAppointmentDTO.getId_appointment())  ));
            return new CustomResponse<>(null,false,200,"El estatus de la cita se actualizo 'ACEPTADA'");
        }catch (Exception e){
            return new CustomResponse<>(null,true,400, "Error al actualizar la cita");
        }
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Appointment> updateStatusToCancelled(IdAppointmentDTO idAppointmentDTO, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }if(!this.repository.existsById(idAppointmentDTO.getId_appointment())){
                return new CustomResponse<>(null,true,400,"La cita no existe");
            }else if(this.repository.statusAppointmentIs_Name(idAppointmentDTO.getId_appointment(),"Denegada")|| this.repository.statusAppointmentIs_Name(idAppointmentDTO.getId_appointment(),"Finalizada")){
                return new CustomResponse<>(null,true,400,"La cita no se puede cancelar debido a su estatus actual");
            }else if(this.repository.statusAppointmentIs_Name(idAppointmentDTO.getId_appointment(),"Cancelada'")){
                return new CustomResponse<>(null,true,400,"La cita ya esta CANCELADA");
            }
            mailManeger.sendMessge(new EmailDTO("Cita cancelada", repository.getEmailOfPatient(idAppointmentDTO.getId_appointment())
                    , "CANCELADA", repository.getDateAppointment(idAppointmentDTO.getId_appointment())
                    ,repository.getAppointmentSpeciality(idAppointmentDTO.getId_appointment())  ));
            this.repository.updateStatusToCancelled(idAppointmentDTO.getId_appointment());

            return new CustomResponse<>(null,false,200,"El estatus de la cita se actualizo 'CANCELADA'");
        }catch (Exception e){
            return new CustomResponse<>(null,true,400, "Error al actualizar la cita");
        }
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Appointment> updateStatusToFinished(IdAppointmentDTO idAppointmentDTO, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }else if(!this.repository.existsById(idAppointmentDTO.getId_appointment())){
                return new CustomResponse<>(null,true,400,"La cita no existe");
            }else if(this.repository.statusAppointmentIs_Name(idAppointmentDTO.getId_appointment(),"Denegada")|| this.repository.statusAppointmentIs_Name(idAppointmentDTO.getId_appointment(),"Cancelada")){
                return new CustomResponse<>(null,true,400,"La cita no se puede finalizar debido a su estatus actual");
            }else if(this.repository.statusAppointmentIs_Name(idAppointmentDTO.getId_appointment(),"Finalizada'")){
                return new CustomResponse<>(null,true,400,"La cita ya esta FINALIZADA");
            }
            this.repository.updateStatusToFinished(idAppointmentDTO.getId_appointment());
            mailManeger.sendMessge(new EmailDTO("Cita Finalizada", repository.getEmailOfPatient(idAppointmentDTO.getId_appointment())
                    , "Finalizada", repository.getDateAppointment(idAppointmentDTO.getId_appointment())
                    ,repository.getAppointmentSpeciality(idAppointmentDTO.getId_appointment())  ));
            return new CustomResponse<>(null,false,200,"El estatus de la cita se actualizo 'CANCELADA'");
        }catch (Exception e){
            return new CustomResponse<>(null,true,400, "Error al actualizar la cita");
        }
    }


}


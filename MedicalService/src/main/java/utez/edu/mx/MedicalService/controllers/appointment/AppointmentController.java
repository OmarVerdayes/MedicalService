package utez.edu.mx.MedicalService.controllers.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.MedicalService.controllers.appointment.DTO.AppointmentDTO;
import utez.edu.mx.MedicalService.controllers.appointment.DTO.IdAppointmentDTO;
import utez.edu.mx.MedicalService.models.appointment.Appointment;
import utez.edu.mx.MedicalService.services.appointment.AppointmentService;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/appointment")
@CrossOrigin(origins = {"*"})
public class AppointmentController {
    @Autowired
    AppointmentService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Appointment>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @GetMapping("/finish/doctor/{id_doctor}")
    public ResponseEntity<CustomResponse<List<Appointment>>> getFinishAppointmentDoctor(@PathVariable("id_doctor") Long id_doctor){
        return new ResponseEntity<>(this.service.getFinishAppointmentDoctor(id_doctor), HttpStatus.OK);
    }
    @GetMapping("/cancelled/doctor/{id_doctor}")
    public ResponseEntity<CustomResponse<List<Appointment>>> getCancelledAppointmentDoctor(@PathVariable("id_doctor") Long id_doctor){
        return new ResponseEntity<>(this.service.getCancelledAppointmentDoctor(id_doctor), HttpStatus.OK);
    }
    @GetMapping("/patient/{id_patient}/{status}")
    public ResponseEntity<CustomResponse<List<Appointment>>> getAppointmentPatientByStatus(@PathVariable("id_patient") Long id_patient,@PathVariable("status") String status){
        return new ResponseEntity<>(this.service.getAppointmentPatientByStatus(id_patient,status), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Appointment>> insert(@Validated @RequestBody AppointmentDTO appointmentDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.insert(appointmentDTO,bindingResult),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Appointment>> update(@Validated @RequestBody AppointmentDTO appointmentDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.update(appointmentDTO,bindingResult),HttpStatus.CREATED);
    }
    @DeleteMapping("/")
    public ResponseEntity<CustomResponse<Appointment>> delete(@Validated @RequestBody Map<String,Long> requets){
        Long id=requets.get("id");
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.CREATED);
    }
    @PatchMapping("/cancelled")
    public ResponseEntity<CustomResponse<Appointment>> updateStatusToCancelled(@Validated @RequestBody IdAppointmentDTO idAppointmentDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.updateStatusToCancelled(idAppointmentDTO,bindingResult),HttpStatus.CREATED);
    }
    @PatchMapping("/admin/denied")
    public ResponseEntity<CustomResponse<Appointment>> updateStatusToDenied(@Validated @RequestBody IdAppointmentDTO idAppointmentDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.updateStatusToDenied(idAppointmentDTO,bindingResult),HttpStatus.CREATED);
    }
    @PatchMapping("/admin/accepted")
    public ResponseEntity<CustomResponse<Appointment>> updateStatusToAccepted(@Validated @RequestBody IdAppointmentDTO idAppointmentDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.updateStatusToAccepted(idAppointmentDTO,bindingResult),HttpStatus.CREATED);
    }
    @PatchMapping("/doctor/finished")
    public ResponseEntity<CustomResponse<Appointment>> updateStatusToFinished(@Validated @RequestBody IdAppointmentDTO idAppointmentDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.updateStatusToFinished(idAppointmentDTO,bindingResult),HttpStatus.CREATED);
    }


}
//updateStatusToFinished
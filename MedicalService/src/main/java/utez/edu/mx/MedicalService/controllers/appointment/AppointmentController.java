package utez.edu.mx.MedicalService.controllers.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.MedicalService.models.appointment.Appointment;
import utez.edu.mx.MedicalService.services.AppointmentService;
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
}

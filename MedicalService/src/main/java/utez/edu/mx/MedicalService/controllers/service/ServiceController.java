package utez.edu.mx.MedicalService.controllers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.MedicalService.controllers.appointment.AppointmentDTO;
import utez.edu.mx.MedicalService.models.appointment.Appointment;
import utez.edu.mx.MedicalService.models.service.ServiceM;
import utez.edu.mx.MedicalService.services.AppointmentService;
import utez.edu.mx.MedicalService.services.ServiceService;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/service")
@CrossOrigin(origins = {"*"})
public class ServiceController {
    @Autowired
    ServiceService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<ServiceM>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<ServiceM>> insert(@Validated @RequestBody ServiceDTO serviceDTO){
        return new ResponseEntity<>(this.service.insert(serviceDTO),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<ServiceM>> update(@Validated @RequestBody ServiceDTO serviceDTO){
        return new ResponseEntity<>(this.service.update(serviceDTO),HttpStatus.CREATED);
    }
    @DeleteMapping("/")
    public ResponseEntity<CustomResponse<ServiceM>> delete(@Validated @RequestBody Map<String,Long> requets){
        Long id=requets.get("id");
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.CREATED);
    }
}
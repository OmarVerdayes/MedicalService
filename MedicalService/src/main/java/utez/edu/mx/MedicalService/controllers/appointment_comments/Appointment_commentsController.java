package utez.edu.mx.MedicalService.controllers.appointment_comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.MedicalService.controllers.appointment_comments.DTO.Appointment_commentsDTO;
import utez.edu.mx.MedicalService.models.appointment_comments.Appointment_comments;
import utez.edu.mx.MedicalService.services.appointment_comments.Appointment_commentsService;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointment_comments")
@CrossOrigin(origins = {"*"})
public class Appointment_commentsController {
    @Autowired
    Appointment_commentsService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Appointment_comments>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Appointment_comments>> insert(@Validated @RequestBody Appointment_commentsDTO appointmentCommentsDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.insert(appointmentCommentsDTO,bindingResult),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Appointment_comments>> update(@Validated @RequestBody Appointment_commentsDTO appointmentCommentsDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.update(appointmentCommentsDTO,bindingResult),HttpStatus.CREATED);
    }
    @DeleteMapping("/")
    public ResponseEntity<CustomResponse<Appointment_comments>> delete(@Validated @RequestBody Map<String,Long> requets){
        Long id=requets.get("id");
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.CREATED);
    }
}



package utez.edu.mx.MedicalService.controllers.doctor_comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.MedicalService.controllers.doctor_comments.DTO.Doctor_commentsDTO;
import utez.edu.mx.MedicalService.models.doctor_comments.Doctor_comments;
import utez.edu.mx.MedicalService.services.doctor_comments.Doctor_commentsService;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctor_comments")
@CrossOrigin(origins = {"*"})
public class Doctor_commentsController {
    @Autowired
    Doctor_commentsService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Doctor_comments>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Doctor_comments>> insert(@Validated @RequestBody Doctor_commentsDTO doctorcommentsDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.insert(doctorcommentsDTO,bindingResult),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Doctor_comments>> update(@Validated @RequestBody Doctor_commentsDTO doctorcommentsDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.update(doctorcommentsDTO,bindingResult),HttpStatus.CREATED);
    }
    @DeleteMapping("/")
    public ResponseEntity<CustomResponse<Doctor_comments>> delete(@Validated @RequestBody Map<String,Long> requets){
        Long id=requets.get("id");
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.CREATED);
    }
}


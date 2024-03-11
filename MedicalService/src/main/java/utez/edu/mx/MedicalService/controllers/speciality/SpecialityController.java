package utez.edu.mx.MedicalService.controllers.speciality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.MedicalService.controllers.appointment.AppointmentDTO;
import utez.edu.mx.MedicalService.models.appointment.Appointment;
import utez.edu.mx.MedicalService.models.speciality.Speciality;
import utez.edu.mx.MedicalService.services.AppointmentService;
import utez.edu.mx.MedicalService.services.SpecialityService;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/speciality")
@CrossOrigin(origins = {"*"})

public class SpecialityController {
    @Autowired
    SpecialityService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Speciality>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Speciality>> insert(@Validated @RequestBody SpecialityDTO specialityDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.insert(specialityDTO,bindingResult),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Speciality>> update(@Validated @RequestBody SpecialityDTO specialityDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.update(specialityDTO,bindingResult),HttpStatus.CREATED);
    }
    @DeleteMapping("/")
    public ResponseEntity<CustomResponse<Speciality>> delete(@Validated @RequestBody Map<String,Long> requets){
        Long id=requets.get("id");
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.CREATED);
    }
}

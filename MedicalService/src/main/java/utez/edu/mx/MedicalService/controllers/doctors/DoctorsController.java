package utez.edu.mx.MedicalService.controllers.doctors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.MedicalService.controllers.doctors.DTO.DoctorDTO;
import utez.edu.mx.MedicalService.models.doctors.Doctors;
import utez.edu.mx.MedicalService.services.docstors.DoctorsService;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = {"*"})
public class DoctorsController {
    @Autowired
    DoctorsService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Doctors>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Doctors>> insert(@Validated @RequestBody DoctorDTO doctorDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.insert(doctorDTO,bindingResult),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Doctors>> update(@Validated @RequestBody DoctorDTO doctorDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.update(doctorDTO,bindingResult),HttpStatus.CREATED);
    }
    @DeleteMapping("/")
    public ResponseEntity<CustomResponse<Doctors>> delete(@Validated @RequestBody Map<String,Long> requets){
        Long id=requets.get("id");
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.CREATED);
    }
}

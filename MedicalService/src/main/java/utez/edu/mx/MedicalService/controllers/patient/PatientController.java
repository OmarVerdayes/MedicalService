package utez.edu.mx.MedicalService.controllers.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.MedicalService.controllers.patient.DTO.PatientDTO;
import utez.edu.mx.MedicalService.models.patient.Patient;
import utez.edu.mx.MedicalService.services.patient.PatientService;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = {"*"})

public class PatientController {

    @Autowired
    PatientService service;


    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Patient>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Patient>> insert(@Validated @RequestBody PatientDTO patientDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.insert(patientDTO,bindingResult),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Patient>> update(@Validated @RequestBody PatientDTO patientDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.update(patientDTO,bindingResult),HttpStatus.CREATED);
    }
    @DeleteMapping("/")
    public ResponseEntity<CustomResponse<Patient>> delete(@Validated @RequestBody Map<String,Long> requets){
        Long id=requets.get("id");
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.CREATED);
    }
}

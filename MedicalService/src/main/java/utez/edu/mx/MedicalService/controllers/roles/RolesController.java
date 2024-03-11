package utez.edu.mx.MedicalService.controllers.roles;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.MedicalService.controllers.appointment.AppointmentDTO;
import utez.edu.mx.MedicalService.models.appointment.Appointment;
import utez.edu.mx.MedicalService.models.roles.Roles;
import utez.edu.mx.MedicalService.services.AppointmentService;
import utez.edu.mx.MedicalService.services.RolesService;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = {"*"})
public class RolesController {
    @Autowired
    RolesService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Roles>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Roles>> insert(@Validated @RequestBody RolesDTO rolesDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.insert(rolesDTO ,bindingResult),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Roles>> update(@Validated @RequestBody RolesDTO rolesDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.update(rolesDTO,bindingResult),HttpStatus.CREATED);
    }
    @DeleteMapping("/")
    public ResponseEntity<CustomResponse<Roles>> delete(@Validated @RequestBody Map<String,Long> requets){
        Long id=requets.get("id");
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.CREATED);
    }
}
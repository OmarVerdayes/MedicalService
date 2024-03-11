package utez.edu.mx.MedicalService.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.MedicalService.controllers.appointment.AppointmentDTO;
import utez.edu.mx.MedicalService.models.appointment.Appointment;
import utez.edu.mx.MedicalService.models.users.Users;
import utez.edu.mx.MedicalService.services.AppointmentService;
import utez.edu.mx.MedicalService.services.UserService;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"*"})

public class UsersController {
    @Autowired
    UserService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Users>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Users>> insert(@Validated @RequestBody UsersDTO usersDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.insert(usersDTO,bindingResult),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Users>> update(@Validated @RequestBody UsersDTO usersDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.update(usersDTO,bindingResult),HttpStatus.CREATED);
    }
    @DeleteMapping("/")
    public ResponseEntity<CustomResponse<Users>> delete(@Validated @RequestBody Map<String,Long> requets){
        Long id=requets.get("id");
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.CREATED);
    }
}

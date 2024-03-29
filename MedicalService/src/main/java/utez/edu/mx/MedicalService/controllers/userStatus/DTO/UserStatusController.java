package utez.edu.mx.MedicalService.controllers.userStatus.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.MedicalService.controllers.userStatus.UsersStatusDTO;
import utez.edu.mx.MedicalService.models.userStatus.UserStatus;
import utez.edu.mx.MedicalService.services.userStatus.UserStatusService;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/userstatus")
@CrossOrigin(origins = {"*"})

public class UserStatusController {
    @Autowired
    UserStatusService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<UserStatus>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<UserStatus>> insert(@Validated @RequestBody UsersStatusDTO usersStatusDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.insert(usersStatusDTO,bindingResult),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<UserStatus>> update(@Validated @RequestBody UsersStatusDTO usersStatusDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.update(usersStatusDTO,bindingResult),HttpStatus.CREATED);
    }
    @DeleteMapping("/")
    public ResponseEntity<CustomResponse<UserStatus>> delete(@Validated @RequestBody Map<String,Long> requets){
        Long id=requets.get("id");
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.CREATED);
    }
}


package utez.edu.mx.MedicalService.controllers.appointmentStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.MedicalService.models.appointmentStatus.AppointmentStatus;
import utez.edu.mx.MedicalService.services.AppointmentStatusService;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointmentstatus")
@CrossOrigin(origins = {"*"})
public class AppointmentStatusController {
    @Autowired
    AppointmentStatusService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<AppointmentStatus>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<AppointmentStatus>> insert(@Validated @RequestBody AppointmentStatusDTO appointmentStatusDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.insert(appointmentStatusDTO,bindingResult),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<AppointmentStatus>> update(@Validated @RequestBody AppointmentStatusDTO appointmentStatusDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.update(appointmentStatusDTO,bindingResult),HttpStatus.CREATED);
    }
    @DeleteMapping("/")
    public ResponseEntity<CustomResponse<AppointmentStatus>> delete(@Validated @RequestBody Map<String,Long> requets){
        Long id=requets.get("id");
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.CREATED);
    }
}


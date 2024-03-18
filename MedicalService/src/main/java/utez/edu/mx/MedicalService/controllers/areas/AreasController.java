package utez.edu.mx.MedicalService.controllers.areas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.MedicalService.controllers.areas.DTO.AreasDTO;
import utez.edu.mx.MedicalService.models.areas.Areas;
import utez.edu.mx.MedicalService.services.areas.AreasService;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/areas")
@CrossOrigin(origins = {"*"})
public class AreasController {
    @Autowired
    AreasService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Areas>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Areas>> insert(@Validated @RequestBody AreasDTO areasDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.insert(areasDTO,bindingResult),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Areas>> update(@Validated @RequestBody AreasDTO areasDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.update(areasDTO,bindingResult),HttpStatus.CREATED);
    }
    @DeleteMapping("/")
    public ResponseEntity<CustomResponse<Areas>> delete(@Validated @RequestBody Map<String,Long> requets){
        Long id=requets.get("id");
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.CREATED);
    }
}


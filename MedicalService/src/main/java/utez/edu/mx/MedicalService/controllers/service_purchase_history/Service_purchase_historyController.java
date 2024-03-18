package utez.edu.mx.MedicalService.controllers.service_purchase_history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.MedicalService.controllers.areas.DTO.AreasDTO;
import utez.edu.mx.MedicalService.controllers.service_purchase_history.DTO.Service_purchase_historyDTO;
import utez.edu.mx.MedicalService.models.areas.Areas;
import utez.edu.mx.MedicalService.models.service_purchase_history.Service_purchase_history;
import utez.edu.mx.MedicalService.services.service_purchase_history.Service_purchase_historyService;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/purchase_history")
@CrossOrigin(origins = {"*"})
public class Service_purchase_historyController {
    @Autowired
    Service_purchase_historyService service;


    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Service_purchase_history>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{email}")
    public ResponseEntity<CustomResponse<List<Service_purchase_history>>> getUserhistory(@PathVariable("email") String email){
        return new ResponseEntity<>(this.service.userHistory(email), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Service_purchase_history>> insert(@Validated @RequestBody Service_purchase_historyDTO servicePurchaseHistoryDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.insert(servicePurchaseHistoryDTO,bindingResult),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Service_purchase_history>> update(@Validated @RequestBody Service_purchase_historyDTO servicePurchaseHistoryDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.update(servicePurchaseHistoryDTO,bindingResult),HttpStatus.CREATED);
    }
    @DeleteMapping("/")
    public ResponseEntity<CustomResponse<Service_purchase_history>> delete(@Validated @RequestBody Map<String,Long> requets){
        Long id=requets.get("id");
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.CREATED);
    }
}

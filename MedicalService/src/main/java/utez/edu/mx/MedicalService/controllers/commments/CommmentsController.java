package utez.edu.mx.MedicalService.controllers.commments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.MedicalService.controllers.commments.DTO.CommentsDTO;
import utez.edu.mx.MedicalService.models.comments.Comments;
import utez.edu.mx.MedicalService.services.comments.CommentsService;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = {"*"})
public class CommmentsController {
    @Autowired
    CommentsService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Comments>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Comments>> insert(@Validated @RequestBody CommentsDTO commentsDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.insert(commentsDTO,bindingResult),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Comments>> update(@Validated @RequestBody CommentsDTO commentsDTO, BindingResult bindingResult){
        return new ResponseEntity<>(this.service.update(commentsDTO,bindingResult),HttpStatus.CREATED);
    }
    @DeleteMapping("/")
    public ResponseEntity<CustomResponse<Comments>> delete(@Validated @RequestBody Map<String,Long> requets){
        Long id=requets.get("id");
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.CREATED);
    }
}


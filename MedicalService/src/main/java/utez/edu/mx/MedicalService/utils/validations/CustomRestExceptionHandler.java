package utez.edu.mx.MedicalService.utils.validations;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.util.ArrayList;
import java.util.List;

public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
    protected ResponseEntity<Object>
    handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ){
        List<String> errors= new ArrayList<>();
        for (FieldError error: ex.getBindingResult().getFieldErrors()){
            errors.add(error.getField()+":"+error.getDefaultMessage());
        }
        CustomResponse<Object> apiError= new CustomResponse<>(errors,true,400,"Missing fields");
        return handleExceptionInternal(ex,apiError,headers,HttpStatus.BAD_REQUEST,request);
    }
}

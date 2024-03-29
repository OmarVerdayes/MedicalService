package utez.edu.mx.MedicalService.utils;

import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
@NoArgsConstructor
public class ConvertErrorsValidationToString {
    public String convertErrorsValidationToString(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage.append(fieldError.getDefaultMessage()).append("\n"); // Agregar un salto de línea después de cada mensaje de error
        }
        return errorMessage.toString();
    }

}

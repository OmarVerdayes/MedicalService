package utez.edu.mx.MedicalService.utils.validations;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import utez.edu.mx.MedicalService.controllers.doctors.DTO.DoctorDTO;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Time;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EndTimeAfterStartTimeValidator.class)

public @interface EndTimeAfterStartTime {
    String message() default "La hora de fin del turno debe ser mayor a la hora de inicio del turno";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

class EndTimeAfterStartTimeValidator implements ConstraintValidator<EndTimeAfterStartTime, DoctorDTO> {
    @Override
    public void initialize(EndTimeAfterStartTime constraintAnnotation) {
    }

    @Override
    public boolean isValid(DoctorDTO doctor, ConstraintValidatorContext context) {
        Time startTime = doctor.getShift_start_time();
        Time endTime = doctor.getShift_finish_time();
        if (startTime == null || endTime == null) {
            return true;
        }
        return endTime.compareTo(startTime) > 0;
    }
}
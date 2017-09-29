package app.annotations;

import app.validators.ExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistValidator.class)
public @interface Exist {
    String message() default "{Exist}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String model();

    String column();

    String condition() default "";

}
package github.com.miralhas.ecommerce_uol.config.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidatorConstraint.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PhoneNumberValidator {
    String message() default "Número de telefone inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


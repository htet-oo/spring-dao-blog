package springblog.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import springblog.validation.impl.EmailValidation;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidation.class)
public @interface EmailExit {
    String message() default "Your Email can't be finded.";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};
}

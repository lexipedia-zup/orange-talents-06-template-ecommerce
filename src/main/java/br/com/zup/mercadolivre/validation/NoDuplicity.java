package br.com.zup.mercadolivre.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy =  NoDuplicityValidator.class)
public @interface NoDuplicity {
    String message() default "Há um valor duplicado.";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default{};


}

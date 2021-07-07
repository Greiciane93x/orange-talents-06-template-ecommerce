package br.com.zupacademy.ane.mercadolivre.mercadolivre.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {ExistsIdValidator.class}
)
public @interface ExistsId {

    String message() default "Já existe um valor cadastrado!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String fieldName();
    Class<?> domainClass();
}

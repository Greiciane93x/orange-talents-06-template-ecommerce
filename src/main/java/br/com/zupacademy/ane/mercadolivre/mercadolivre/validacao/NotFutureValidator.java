package br.com.zupacademy.ane.mercadolivre.mercadolivre.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Duration;
import java.time.LocalDate;

public class NotFutureValidator implements ConstraintValidator<NotFuture, Object> {
    private String atributoUnico;
    private Class<?> classe;

    @Override
    public void initialize(NotFuture params) {
        atributoUnico = params.fieldName();
        classe = params.domainClass();
    }

    @Override
    public boolean isValid(Object valor, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate hoje = LocalDate.now();
        LocalDate dataRequest = (LocalDate) valor;
        Duration diff = Duration.between(hoje.atStartOfDay(), dataRequest.atStartOfDay());
        if(diff.isZero()){
            return true;
        } else if (diff.isNegative()){
            return true;
        }
        return false;
    }
}

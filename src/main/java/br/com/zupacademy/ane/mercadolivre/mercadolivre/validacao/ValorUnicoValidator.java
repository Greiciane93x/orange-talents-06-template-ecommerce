package br.com.zupacademy.ane.mercadolivre.mercadolivre.validacao;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object> {
    private String atributoUnico;
    private Class<?> classe;
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ValorUnico params) {
        atributoUnico = params.fieldName();
        classe = params.domainClass();
    }

    @Override
    public boolean isValid(Object valor, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("select 1 from " + classe.getName() + " where " + atributoUnico + "=:valor");
        query.setParameter("valor", valor);
        List<?> lista = query.getResultList();
        Assert.isTrue(lista.size()<=1, "Foi encontrado mais de uma"+classe+"com o atributo"+atributoUnico);
        return lista.isEmpty();
    }
}

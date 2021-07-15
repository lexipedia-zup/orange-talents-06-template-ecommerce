package br.com.zup.mercadolivre.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIfNotNullValidator implements ConstraintValidator<ExistsIfNotNull, Object> {
    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> classe;

    private String atributo;

    @Override
    public void initialize(ExistsIfNotNull constraintAnnotation) {
        atributo = constraintAnnotation.fieldName();
        classe = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if(value == null){
            return true;
        }

        Query query = entityManager.createQuery("select 1 from " + classe.getName() + " where " + atributo + "=:value");
        query.setParameter("value", value);
        List<?> lista = query.getResultList();
        return !lista.isEmpty();
    }
}

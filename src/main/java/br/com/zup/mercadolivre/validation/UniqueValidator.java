package br.com.zup.mercadolivre.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {
    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> classe;

    private String atributo;

    @Override
    public void initialize(Unique constraintAnnotation) {
        atributo = constraintAnnotation.fieldName();
        classe = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        Query query = entityManager.createQuery("select 1 from " + classe.getName()+" where "+atributo+"=:value");
        query.setParameter("value", value);
        List<?> lista = query.getResultList();

        return lista.isEmpty();
    }
}

package br.com.zup.mercadolivre.validation;

import br.com.zup.mercadolivre.product.particular.ParticularRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NoDuplicityValidator implements ConstraintValidator<NoDuplicity, List<ParticularRequest>> {

    @Override
    public void initialize(NoDuplicity constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<ParticularRequest> value, ConstraintValidatorContext context) {
        Set<String> nameSet = new HashSet<>();
        List<String> nameList = new ArrayList<>();

        for(ParticularRequest request: value){
            nameList.add(request.getName());
            nameSet.add(request.getName());
        }

        if(nameList.size() == nameSet.size()){
            return true;
        }

        return false;
    }
}

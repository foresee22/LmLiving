package com.lm.lmliving.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumConstraintZhengzeValidator implements ConstraintValidator<EnumZhengzeValidate,Integer> {
    private String regexp;
    @Override
    public void initialize(EnumZhengzeValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return integer.toString().matches(regexp);
    }
}

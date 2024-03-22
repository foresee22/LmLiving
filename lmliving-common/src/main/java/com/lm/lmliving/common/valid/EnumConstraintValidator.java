package com.lm.lmliving.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * 1.EnumConstraintValidator是真正的校验器，即校验的逻辑是写在这里的，这个校验器是自定义的
 * 2.EnumConstraintValidator需要实现接口ConstraintValidator
 * 3.<EnumValidate,Integer>.EnumValidate表示关联哪个注解，Integer表示表单传过来的值(对应的属性是Integer类型)，也就是Entity对应字段的数据类型，
 * 表示该校验器是针对@EnumValidate注解下传入的Entity类的Integer类型的属性的值进行校验
 */
public class EnumConstraintValidator implements ConstraintValidator<EnumValidate,Integer> {
    //定义一个set集合，用于收集EnumValidate传入values
    private Set<Integer> set = new HashSet<>();
    @Override
    public void initialize(EnumValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        // 通过反射获取@EnumValidate注解上指定的values定义的值
        // values是@EnumValidate注解上指定的values定义的值
        int[] values = constraintAnnotation.values();
        for (int value : values) {
            set.add(value);
        }
    }
    // 如果返回true表示验证成功-通过
    // 返回false,表示验证失败-没有通过
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        // integer是表单提交过来的值
        return set.contains(integer);
    }
}

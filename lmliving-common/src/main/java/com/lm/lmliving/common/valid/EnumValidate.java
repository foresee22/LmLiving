package com.lm.lmliving.common.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 1.创建自定义的注解EnumValidate参考注解@NotNull源码来开发
 * 2.@Constraint(validatedBy={EnumConstraintValidator.class})可以指定该自定义注解和EnumConstraintValidator(这个校验器是自定义的)校验器关联
 * 3.String message() default"{?}";可以指定校验时，返回的信息
 * 4.validatedBy = {} 是一个数组，可以传入多个校验器，可以由多个校验器来组合校验
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {EnumConstraintValidator.class}
)
public @interface EnumValidate {
    String message() default "{com.lm.lmliving.common.valid.EnumValidate.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // 增加values属性
    int[] values() default {};
}

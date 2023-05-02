package com.oi.promocoesweb.validation.annotation;

import com.oi.promocoesweb.validation.enums.ValidationEnum;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldValidation {
    ValidationEnum name() default ValidationEnum.SEQUENCIA_VALORES;
}

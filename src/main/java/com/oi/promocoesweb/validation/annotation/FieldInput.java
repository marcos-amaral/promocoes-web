package com.oi.promocoesweb.validation.annotation;

import com.oi.promocoesweb.template.entity.ui.InputType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldInput {
    InputType name() default InputType.STRING;
    String mask() default "";
    int maxLength() default 2500;
    String label() default "";
    String[] autoComplete() default {""};
}

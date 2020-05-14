package com.tigerobo.validation.annotation;

import com.tigerobo.validation.condition.NoCondition;
import com.tigerobo.validation.strategy.Condition;
import com.tigerobo.validation.strategy.ValidationStrategy;

import java.lang.annotation.*;

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TigeroboValidation {

    Class fieldType();
    Class<? extends ValidationStrategy>[] validMethod();
    Class<? extends Condition>[] tragger();
}

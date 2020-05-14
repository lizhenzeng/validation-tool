package com.tigerobo.validation.strategy;

public interface Condition {

    ValidationStrategy getValidationStrategy();

    Boolean enoughCondition(Object target);


}

package com.tigerobo.validation.strategy;

public interface ValidationStrategy {
    Class getValidationFieldClass();

    Boolean isNeedValidation(Object value);

    Boolean isPass(Object value);

    String ifNotPassPresention(Object value,String fieldName,String template);
}

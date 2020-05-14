package com.tigerobo.validation.basestrategy;

import com.tigerobo.validation.strategy.ValidationStrategy;

public class StringNotEmpty implements ValidationStrategy {
    public StringNotEmpty(){}
    @Override
    public Class getValidationFieldClass(){
        return String.class;
    }

    @Override
    public Boolean isNeedValidation(Object value){
        return value.getClass().isAssignableFrom(String.class)?true:false;
    }

    @Override
    public Boolean isPass(Object value){
        String str = (String) value;
        return str!=null && str.trim().length()>0;
    }
    @Override
    public String ifNotPassPresention(Object value,String fieldName,String template){
        if(template == null){
            template = "The %s's field is Empty";
        }
        return String.format(template,fieldName);
    }
}

package com.tigerobo.validation.metadata;

import com.tigerobo.validation.annotation.TigeroboValidation;
import com.tigerobo.validation.config.Configuration;
import com.tigerobo.validation.strategy.Condition;
import com.tigerobo.validation.strategy.ValidationStrategy;
import com.tigerobo.validation.utils.ClassUtils;
import lombok.Data;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnnotationMetaData {

    private Annotation an;

    private Configuration configuration;

    private Object target;

    private String fieldName;

    private Field field;

    private Class fieldClazz;

    private Object value;

    private Boolean isPass =  false;

    private String message;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Annotation getAn() {
        return an;
    }

    public void setAn(Annotation an) {
        this.an = an;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Class getFieldClazz() {
        return fieldClazz;
    }

    public void setFieldClazz(Class fieldClazz) {
        this.fieldClazz = fieldClazz;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Boolean getPass() {
        return isPass;
    }

    public void setPass(Boolean pass) {
        isPass = pass;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Class<? extends ValidationStrategy>> getValidationStrategys() {
        return validationStrategys;
    }

    public void setValidationStrategys(List<Class<? extends ValidationStrategy>> validationStrategys) {
        this.validationStrategys = validationStrategys;
    }

    public List<Class<? extends Condition>> getConditions() {
        return conditions;
    }

    public void setConditions(List<Class<? extends Condition>> conditions) {
        this.conditions = conditions;
    }

    public AnnotationMetaData(Annotation an, Field field, Object target){
        this.an = an;
        if(an!=null){
            conditions.addAll(Arrays.stream(((TigeroboValidation) an).tragger()).collect(Collectors.toList()));
            validationStrategys.addAll(Arrays.stream(((TigeroboValidation) an).validMethod()).collect(Collectors.toList()));
        }
        this.field = field;
        this.target = target;
        this.fieldClazz = field.getClass();
        this.fieldName=field.getName();
        try {
            field.setAccessible(true);
            this.value = field.get(target);
        }catch (Exception e){}
    }

    private List<Class<? extends ValidationStrategy>> validationStrategys = new ArrayList<>();

    private List<Class<? extends Condition>> conditions = new ArrayList<>();



}

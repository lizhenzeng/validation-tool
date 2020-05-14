package com.tigerobo.validation.strategy;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public class WrapMap {
    public Map<Class,ValidationStrategy> conditionValidationStrategyHashMap = new HashMap<>();
    public  Map<String,ValidationStrategy> validationStrategyList = new HashMap<>();

    public Map<String, ValidationStrategy> getValidationStrategyList() {
        return validationStrategyList;
    }

    public void setValidationStrategyList(Map<String, ValidationStrategy> validationStrategyList) {
        this.validationStrategyList = validationStrategyList;
    }

    public Map<Class, ValidationStrategy> getConditionValidationStrategyHashMap() {
        return conditionValidationStrategyHashMap;
    }

    public void setConditionValidationStrategyHashMap(Map<Class, ValidationStrategy> conditionValidationStrategyHashMap) {
        this.conditionValidationStrategyHashMap = conditionValidationStrategyHashMap;
    }
}

package com.tigerobo.validation.config;

import com.tigerobo.validation.condition.NoCondition;
import com.tigerobo.validation.metadata.AnnotationMetaData;
import com.tigerobo.validation.strategy.Condition;
import com.tigerobo.validation.strategy.ConditionRegister;
import com.tigerobo.validation.strategy.ValidationStrategy;
import com.tigerobo.validation.strategy.ValidationStrategyRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;

import java.util.List;

@org.springframework.context.annotation.Configuration
@AutoConfigureAfter({ValidationStrategyRegister.class,ConditionRegister.class})
public class Configuration {

    @Autowired
    ValidationStrategyRegister validationStrategyRegister;
    @Autowired
    ConditionRegister conditionRegister;

    public ValidationStrategyRegister getValidationStrategyRegister() {
        return validationStrategyRegister;
    }

    public void setValidationStrategyRegister(ValidationStrategyRegister validationStrategyRegister) {
        this.validationStrategyRegister = validationStrategyRegister;
    }

    public ConditionRegister getConditionRegister() {
        return conditionRegister;
    }

    public void setConditionRegister(ConditionRegister conditionRegister) {
        this.conditionRegister = conditionRegister;
    }

    public AnnotationMetaData process(List<Class<? extends Condition>> conditions, List<Class<? extends ValidationStrategy>> validationStrategys, AnnotationMetaData target){

        if((conditions!=null && !conditions.isEmpty())
                || (conditions!=null && conditions.size() ==1 && conditions.get(0).getName().equalsIgnoreCase(NoCondition.class.getName()))){
            conditions.forEach(val->enoughConditionToValidation(val,target,null));
        }else{
            if(validationStrategys!=null && !validationStrategys.isEmpty()){
                validationStrategys.forEach(val->enoughConditionToValidation(null,target,val));
            }
        }
        return target;
    }





    private void enoughConditionToValidation(Class<? extends Condition> conditionClzz, AnnotationMetaData target,Class<? extends ValidationStrategy> validationStrategy){
        ValidationStrategy vs = conditionRegister.getValidationStrategyByCondition(conditionClzz);
        if(vs==null && validationStrategy!=null){
            vs = validationStrategyRegister.getWrapMap().getValidationStrategyList().get(validationStrategy.getName());
        }
        if(vs!=null){
            if(vs.isNeedValidation(target.getValue()) && vs.isPass(target.getValue())){
                target.setPass(true);
            }else{
                target.setMessage(vs.ifNotPassPresention(target.getValue(),target.getFieldName(),null));
            }
        }

    }





}

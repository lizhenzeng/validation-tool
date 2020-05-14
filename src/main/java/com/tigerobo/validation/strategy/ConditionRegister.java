package com.tigerobo.validation.strategy;

public class ConditionRegister  {

    private  WrapMap wrapMap;

    public WrapMap getWrapMap() {
        return wrapMap;
    }

    public void setWrapMap(WrapMap wrapMap) {
        this.wrapMap = wrapMap;
    }

    public ValidationStrategy getValidationStrategyByCondition(Class<? extends Condition> clzz){
        return wrapMap.getConditionValidationStrategyHashMap().get(clzz);
    }

    public void setConditionValidationStrategyHashMap(Class<? extends Condition> clzz){
        try {
            wrapMap.getConditionValidationStrategyHashMap().put(clzz,clzz.newInstance().getValidationStrategy());
        }catch (Exception e){ }
    }



}

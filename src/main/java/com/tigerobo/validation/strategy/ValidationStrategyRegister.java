package com.tigerobo.validation.strategy;

public class ValidationStrategyRegister{

    private  WrapMap wrapMap = new WrapMap();

    public WrapMap getWrapMap() {
        return wrapMap;
    }

    public void setWrapMap(WrapMap wrapMap) {
        this.wrapMap = wrapMap;
    }

    public void registerValidationStrategy(Class<? extends ValidationStrategy> clzz){
        try {
            registerValidationStrategy(clzz.getName(),clzz.newInstance());
        }catch (Exception e){}
    }

    public void registerValidationStrategy(String fieldClassName,ValidationStrategy vs){
        wrapMap.getValidationStrategyList().put(fieldClassName,vs);
    }

    public ValidationStrategy getValidationStrategyByfieldType(Class fieldType){
        return wrapMap.getValidationStrategyList().get(fieldType);
    }



}

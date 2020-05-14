package com.tigerobo.validation.condition;

import com.tigerobo.validation.basestrategy.StringNotEmpty;
import com.tigerobo.validation.strategy.Condition;
import com.tigerobo.validation.strategy.ValidationStrategy;

public class NoCondition implements Condition {
    public NoCondition(){}
    @Override
    public ValidationStrategy getValidationStrategy() {
        return new StringNotEmpty();
    }

    @Override
    public Boolean enoughCondition(Object target) {
        return true;
    }
}

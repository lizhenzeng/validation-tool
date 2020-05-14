package com.tigerobo.sample.condition;

import com.tigerobo.validation.basestrategy.StringNotEmpty;
import com.tigerobo.validation.strategy.Condition;
import com.tigerobo.validation.strategy.ValidationStrategy;

public class NewCondition implements Condition {
    public NewCondition(){}
    @Override
    public ValidationStrategy getValidationStrategy() {
        return new StringNotEmpty();
    }

    @Override
    public Boolean enoughCondition(Object target) {
        return true;
    }
}

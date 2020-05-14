package com.tigerobo.sample.entity;

import com.tigerobo.validation.annotation.TigeroboValidation;
import com.tigerobo.validation.basestrategy.StringNotEmpty;
import com.tigerobo.validation.condition.NoCondition;
import lombok.Data;

@Data
public class Sample {
    @TigeroboValidation(fieldType = String.class,validMethod = {StringNotEmpty.class},tragger = {NoCondition.class})
    private String name;
    private String value;
}

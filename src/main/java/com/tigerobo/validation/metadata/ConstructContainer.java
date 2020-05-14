package com.tigerobo.validation.metadata;

import com.tigerobo.validation.annotation.TigeroboValidation;
import com.tigerobo.validation.config.Configuration;
import lombok.Data;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ConstructContainer {
    private Object target;

    private List<Field> fields;

    private Configuration configuration;

    private String message;

    private Boolean isPass = false;

    public ConstructContainer(Object target,List<Field> fields,Configuration configuration){
        this.target = target;
        this.fields = fields;
        this.configuration = configuration;
    }


    private List<AnnotationMetaData> convertField2AnnotationMetaData(){
        return fields.stream().map(var->new AnnotationMetaData(var.getDeclaredAnnotation(TigeroboValidation.class),var,target)).collect(Collectors.toList());
    }


    public ConstructContainer valid(){
        if(fields!=null && !fields.isEmpty()){
            List<AnnotationMetaData> amds = convertField2AnnotationMetaData();
            List<AnnotationMetaData> notPassField = amds.stream().map(val->configuration.process(val.getConditions(),val.getValidationStrategys(),val)).filter(val->!val.getPass()).collect(Collectors.toList());
            if(notPassField!=null && !notPassField.isEmpty()){
                message = notPassField.stream().map(val->val.getMessage()).collect(Collectors.joining(","));
            }
        }else{
            isPass = true;
        }
        return this;
    }






    public static class Builder{

        private Object target;

        private List<Field> fields;
        private Configuration configuration;

        private Class<? extends Annotation> an;



        public Builder setTarget(Object target){
            this.target = target;
            return this;
        }
        public Builder setConfiguration(Configuration configuration){
            this.configuration = configuration;
            return this;
        }
        public Builder setAnnotation(Class<? extends Annotation> an){
            this.an = an;
            return this;
        }

        public void getCandidatesField(Object value,Class<? extends Annotation> an){
            try {
                Field[] fs = value.getClass().getDeclaredFields();
                fields = Arrays.stream(fs).map(val-> val.getDeclaredAnnotation(an)!=null?val:null).filter(val->val!=null).collect(Collectors.toList());
            }catch (Exception e){}
        }

        public ConstructContainer build(){
            if(an!=null && target!=null){
                getCandidatesField(target,an);
            }
            return new ConstructContainer(target,fields,configuration);
        }
    }
}

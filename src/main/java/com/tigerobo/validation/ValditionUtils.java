package com.tigerobo.validation;

import com.tigerobo.validation.annotation.TigeroboValidation;
import com.tigerobo.validation.config.Configuration;
import com.tigerobo.validation.metadata.ConstructContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;


@org.springframework.context.annotation.Configuration
@AutoConfigureAfter({Configuration.class})
public class ValditionUtils {
    @Autowired
    Configuration configuration;

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public  ConstructContainer getNotPassConstructContainer(Object target){
        ConstructContainer cc = new ConstructContainer.Builder()
                .setAnnotation(TigeroboValidation.class)
                .setTarget(target)
                .setConfiguration(configuration)
                .build();
        cc.valid();
        return cc;
    }


}

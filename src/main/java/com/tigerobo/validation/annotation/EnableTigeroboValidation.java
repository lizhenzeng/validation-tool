package com.tigerobo.validation.annotation;

import com.tigerobo.validation.config.Configuration;
import com.tigerobo.validation.config.MapperAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({ MapperAutoConfiguration.class, Configuration.class})
public @interface EnableTigeroboValidation {
}

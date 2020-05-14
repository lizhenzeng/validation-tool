package com.tigerobo.validation.config;

import com.tigerobo.validation.ValditionUtils;
import com.tigerobo.validation.strategy.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.context.annotation.Configuration
public class MapperAutoConfiguration implements BeanFactoryAware, ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {


    private BeanFactory beanFactory;
    private ResourceLoader resourceLoader;
    private Environment environment;


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {


        List<String> packages = AutoConfigurationPackages.get(this.beanFactory);
        packages.add("com.tigerobo.validation");
        ClassPathMapperScanner classPathMapperScanner = new ClassPathMapperScanner(registry,false);

        List<Class> conditions = classPathMapperScanner.registerBeanByInterface(packages, Condition.class,null);
        Map<String,Object> props = new HashMap<>();
        Map<Class,ValidationStrategy> meta = new HashMap<>();
        for(Class condition: conditions){
            try {
                meta.put(condition,((Condition)condition.newInstance()).getValidationStrategy());
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        WrapMap wm = new WrapMap();
        wm.setConditionValidationStrategyHashMap(meta);
        List<String> packages1 =new ArrayList<>();
        packages1.add("com.tigerobo.validation");
        props.put("wrapMap",wm);
        classPathMapperScanner.registerBeanByInterface(packages1, ConditionRegister.class,props);

        List<Class> validationStrategys = classPathMapperScanner.registerBeanByInterface(packages1, ValidationStrategy.class,null);
        Map<String,Object> props1 = new HashMap<>();
        Map<String,ValidationStrategy> validationStrategyList = new HashMap<>();
        for(Class clzz: validationStrategys){
            try {
                validationStrategyList.put(clzz.getName(),(ValidationStrategy)clzz.newInstance());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        WrapMap wm1 = new WrapMap();
        wm1.setValidationStrategyList(validationStrategyList);
        props1.put("wrapMap",wm1);
        classPathMapperScanner.registerBeanByInterface(packages1, ValidationStrategyRegister.class,props1);
        classPathMapperScanner.registerBeanByInterface(packages1, Configuration.class,null);
        classPathMapperScanner.registerBeanByInterface(packages1, ValditionUtils.class,null);
    }



    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


}

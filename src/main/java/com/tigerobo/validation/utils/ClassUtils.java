package com.tigerobo.validation.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClassUtils {

    public static <T> List<T>  getInstanceByClasses(Class<? extends T>[] clzzs){
       return   Arrays.stream(clzzs).map(val-> {
            try {
                return val.newInstance();
            } catch (Exception e) { }
            return null;
        }).filter(val->val!=null).collect(Collectors.toList());
    }
}

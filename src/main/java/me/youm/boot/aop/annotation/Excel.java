package me.youm.boot.aop.annotation;


import me.youm.boot.aop.enums.Model;

import java.lang.annotation.*;

/**
 * Excel 注解
 * @author youta
 * */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Excel {

    /**
     * clz
     * @return clz
     */
    Class<?> clazz();

    Model model() default Model.WRITE;

}

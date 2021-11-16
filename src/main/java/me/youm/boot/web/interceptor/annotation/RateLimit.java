package me.youm.boot.web.interceptor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author youta
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {

    /**
     * 请求次数
     *
     * 默认 100 秒内限制 10 次访问
     * @return 次数
     * */
    int number() default 10;

    /**
     * 时间限制
     *
     * 默认 100 秒内限制 10 次访问
     * @return 时间
     * */
    long time() default 100;
}

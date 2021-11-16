package me.youm.boot.web.interceptor.annotation;

import java.lang.annotation.*;

/**
 * 忽略多租户
 *
 * @author youta
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface TenantIgnore {

}

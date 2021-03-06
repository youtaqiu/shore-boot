package me.youm.boot.web.interceptor.annotation;

import me.youm.boot.web.interceptor.enums.Scope;

import java.lang.annotation.*;

/**
 * 数据权限注解
 * <p>
 * 优先级：规则模式 自动模式 指定模式
 *
 * @author youta
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DataScope {

    /**
     * 自 动 模 式
     * <p>
     * Auto 模式下根据用户角色定义规则过滤数据
     * <p>
     * 非 Auto 模式下, 采用强制数据权限过滤，即用户角色定义的规则失效
     * @return {@link Scope}
     */
    Scope scope() default Scope.AUTO;


    /**
     * 规 则 模 式
     * <p>
     * Rule 模式下, scope 配置失效
     * @return {@link DataScopeRule}
     */
    DataScopeRule[] rules() default {};

}

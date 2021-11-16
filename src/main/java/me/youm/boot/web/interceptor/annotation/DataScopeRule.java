package me.youm.boot.web.interceptor.annotation;


import me.youm.boot.secure.domain.SysRole;
import me.youm.boot.web.interceptor.enums.Scope;

import java.lang.annotation.*;

/**
 * 数据权限规则注解
 *
 * @author youta
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DataScopeRule {

    /**
     * 权 限 模 式
     * <p>
     * @return {@link Scope} 权限 Scope
     */
    Scope scope();

    /**
     * 角 色 模 式
     * <p>
     * @return {@link SysRole} 角色 Code
     */
    String role();
}

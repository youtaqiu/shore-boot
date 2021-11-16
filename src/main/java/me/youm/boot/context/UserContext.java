package me.youm.boot.context;

import me.youm.boot.secure.domain.SysRole;
import me.youm.boot.secure.domain.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User Context
 *
 * @author youta
 */
@Component
public class UserContext {

    /**
     * Authentication 认证对象
     * @return {@link Authentication}
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * SysUser 当前用户
     * @return {@link SysUser}
     */
    public SysUser getPrincipal() {
        return (SysUser) getAuthentication().getPrincipal();
    }

    /**
     * Username 当前用户名
     * @return username
     */
    public String getUsername() {
        return getPrincipal().getUsername();
    }

    /**
     * nickname 当前用户昵称
     * @return nickname
     */
    public String getNickName() {
        return getPrincipal().getNickname();
    }

    /**
     * UserId 用户编号
     * @return id
     */
    public String getUserId() {
        return getPrincipal().getId();
    }

    /**
     * deptId 当前部门
     * @return deptId
     */
    public String getDeptId() {
        return getPrincipal().getDeptId();
    }

    /**
     * tenantId 当前租户
     * @return tenantId
     */
    public String getTenantId() {
        return getPrincipal().getTenantId();
    }

    /**
     * roles 角色列表
     * @return {@link SysRole}
     */
    public List<SysRole> getRoles() {
        return getPrincipal().getRoles();
    }

}

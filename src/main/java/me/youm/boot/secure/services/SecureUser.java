package me.youm.boot.secure.services;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.youm.boot.secure.domain.SysRole;
import me.youm.boot.web.base.domain.BaseDomain;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 基础 UserInfo 实体, 为安全验证提供支持的公共属性
 * <p>
 *
 * @author youta
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SecureUser extends BaseDomain implements UserDetails, CredentialsContainer {

    /**
     * 编号
     */
    @TableId(value = "id")
    private String id;

    /**
     * 账号
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 启用
     */
    @TableField(value = "enable")
    private Boolean enable;

    /**
     * 锁定
     */
    @TableField(value = "locked")
    private Boolean locked;

    /**
     * 角色
     */
    @TableField(exist = false)
    private List<SysRole> roles;

    /**
     * 权限
     */
    @TableField(exist = false)
    private Set<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}

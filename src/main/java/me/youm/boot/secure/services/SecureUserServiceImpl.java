package me.youm.boot.secure.services;

import me.youm.boot.secure.domain.SysPower;
import me.youm.boot.secure.domain.SysRole;
import me.youm.boot.secure.domain.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Security 用户服务实现
 *
 * @author youta
 */
@Component
public class SecureUserServiceImpl implements UserDetailsService {

    @Resource
    private IUserService userService;

    @Resource
    private IPowerService powerService;

    /**
     * 加载用户信息
     */
    @Override
    public SecureUser loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.selectByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("USERNAME NOT SUPPORT");
        }
        sysUser.setAuthorities(loadAuthorities(sysUser.getId()));
        sysUser.setRoles(loadRoles(sysUser.getId()));
        return sysUser;
    }

    public Set<? extends GrantedAuthority> loadAuthorities(String userId) {
        Set<SimpleGrantedAuthority> authoritySet = new HashSet<>();
        List<SysPower> powers = powerService.power(userId);
        for (SysPower power : powers) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(power.getCode());
            authoritySet.add(authority);
        }
        return authoritySet;
    }

    public List<SysRole> loadRoles(String userId) {
        return userService.role(userId);
    }
}

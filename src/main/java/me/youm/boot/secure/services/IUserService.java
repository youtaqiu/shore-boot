package me.youm.boot.secure.services;

import me.youm.boot.secure.domain.SysDept;
import me.youm.boot.secure.domain.SysRole;
import me.youm.boot.secure.domain.SysUser;

import java.util.List;

/**
 * @author xiyu
 **/
public interface IUserService {

    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return {@link SysUser}
     */
    SysUser selectByUsername(String username);

    /**
     * 获取角色列表
     *
     * @param userId 用户id
     * @return {@link SysRole}
     */
    List<SysRole> role(String userId);

    /**
     * 获取用户部门 (数据权限)
     *
     * @param userId 用户编号
     * @return {@link SysDept}
     */
    List<SysDept> dept(String userId);

}

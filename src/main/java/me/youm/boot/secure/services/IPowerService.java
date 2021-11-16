package me.youm.boot.secure.services;

import me.youm.boot.secure.domain.SysPower;

import java.util.List;

/**
 * @author xiyu
 **/
public interface IPowerService {

    /**
     * 查询权限列表
     * @param userId 用户id
     * @return {@link SysPower}
     */
    List<SysPower> power(String userId);
}

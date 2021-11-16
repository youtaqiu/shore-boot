package me.youm.boot.context.service;

import me.youm.boot.context.domain.SysLog;

/**
 * @author xiyu
 **/
public interface ILogService {

    /**
     * 保存日志
     * @param sysLog 请求日志
     * @return {@link SysLog}
     */
    SysLog save(SysLog sysLog);
}

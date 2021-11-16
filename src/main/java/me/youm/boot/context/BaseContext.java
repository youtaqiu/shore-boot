package me.youm.boot.context;

import com.alibaba.fastjson.JSON;
import me.youm.boot.aop.enums.Action;
import me.youm.boot.context.domain.SysLog;
import me.youm.boot.context.service.ILogService;
import me.youm.boot.tools.core.ServletUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Base Context
 *
 * @author youta
 */
@Component
public class BaseContext {

    /**
     * 日 志 服 务
     */
    @Resource
    private ILogService sysLogService;

    /**
     * 用 户 服 务
     */
    @Resource
    private UserContext userContext;

    /**
     * 新增日志
     *
     * @param title    标题
     * @param describe 描述
     * @param action   动作
     * @param state    状态
     * @param result   结果
     * @param error    异常
     */
    @Async
    public void record(String title, String describe, Action action, Boolean state, String result, String error) {
        SysLog sysLog = new SysLog();
        sysLog.setTitle(title);
        sysLog.setAction(action);
        sysLog.setDescribe(describe);
        sysLog.setType(ServletUtil.getMethod());
        sysLog.setUrl(ServletUtil.getRequestURI());
        sysLog.setBrowser(ServletUtil.getBrowser());
        sysLog.setCreateBy(userContext.getUserId());
        sysLog.setAddress(ServletUtil.getRemoteHost());
        sysLog.setOperator(userContext.getNickName());
        sysLog.setResult(JSON.toJSONString(result));
        sysLog.setSystem(ServletUtil.getSystem());
        sysLog.setState(state);
        sysLog.setError(error);
        sysLogService.save(sysLog);
    }

}

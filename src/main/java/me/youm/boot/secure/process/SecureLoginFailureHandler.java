package me.youm.boot.secure.process;

import me.youm.boot.aop.enums.Action;
import me.youm.boot.context.BaseContext;
import me.youm.boot.tools.core.ServletUtil;
import me.youm.boot.web.domain.Result;
import me.youm.boot.web.domain.ResultCode;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Describe: Security 登陆失败处理类
 *
 * @author youta
 */
@Component
public class SecureLoginFailureHandler implements AuthenticationFailureHandler {

    @Resource
    private BaseContext context;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {

        e.printStackTrace();

        if (e instanceof UsernameNotFoundException) {
            ServletUtil.writeJson(Result.failure(ResultCode.USER_USERNAME_NOT_FOUND));
            return;
        }
        if (e instanceof LockedException) {
            ServletUtil.writeJson(Result.failure(ResultCode.USER_LOCKED));
            return;
        }
        if (e instanceof BadCredentialsException) {
            ServletUtil.writeJson(Result.failure(ResultCode.USER_BAD_CREDENTIALS));
            return;
        }
        if (e instanceof AccountExpiredException) {
            ServletUtil.writeJson(Result.failure(ResultCode.USER_EXPIRED));
            return;
        }
        if (e instanceof DisabledException) {
            ServletUtil.writeJson(Result.failure(ResultCode.USER_NOT_ENABLE));
            return;
        }

        context.record("登录", "登录失败", Action.AUTH, false, "", "");

        ServletUtil.writeJson(Result.failure(ResultCode.LOGIN_FAILURE));
    }
}

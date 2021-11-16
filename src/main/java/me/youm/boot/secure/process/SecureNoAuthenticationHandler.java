package me.youm.boot.secure.process;

import me.youm.boot.tools.core.ServletUtil;
import me.youm.boot.web.domain.Result;
import me.youm.boot.web.domain.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Security 用户未登陆处理类
 *
 * @author youta
 */
@Component
public class SecureNoAuthenticationHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        ServletUtil.writeJson(Result.failure(ResultCode.NOT_LOGIN));
    }
}

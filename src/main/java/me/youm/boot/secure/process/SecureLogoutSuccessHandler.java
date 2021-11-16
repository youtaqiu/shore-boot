package me.youm.boot.secure.process;

import me.youm.boot.constant.TokenConstant;
import me.youm.boot.secure.uutoken.SecureUserTokenService;
import me.youm.boot.tools.core.ServletUtil;
import me.youm.boot.web.domain.Result;
import me.youm.boot.web.domain.ResultCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Security 注销成功处理类
 *
 * @author youta
 */
@Component
public class SecureLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    private SecureUserTokenService customUserDetailsTokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        String token = httpServletRequest.getHeader(TokenConstant.TOKEN_HEADER).replaceFirst(TokenConstant.TOKEN_PREFIX, "");
        customUserDetailsTokenService.destroyToken(token);
        ServletUtil.writeJson(Result.success(ResultCode.LOGOUT_SUCCESS));
    }
}

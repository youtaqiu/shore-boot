package me.youm.boot.secure.process;

import me.youm.boot.aop.enums.Action;
import me.youm.boot.context.BaseContext;
import me.youm.boot.secure.services.SecureUser;
import me.youm.boot.secure.uutoken.SecureUserToken;
import me.youm.boot.secure.uutoken.SecureUserTokenService;
import me.youm.boot.tools.core.ServletUtil;
import me.youm.boot.web.domain.Result;
import me.youm.boot.web.domain.ResultCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Describe: Security 登陆成功处理类
 * @author youta
 * */
@Component
public class SecureLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private BaseContext context;

    @Resource
    private SecureUserTokenService customUserDetailsTokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
            SecureUser secureUser = (SecureUser) authentication.getPrincipal();

            SecureUserToken userToken = customUserDetailsTokenService.createToken(secureUser);
            String tokenKey = customUserDetailsTokenService.saveToken(userToken);
            String tokenValue = userToken.getToken();

            // 登 录 日 志
            context.record("登录","登录成功", Action.AUTH, true, "","");

            ServletUtil.writeJson(Result.success(ResultCode.LOGIN_SUCCESS,tokenKey,tokenValue));
    }
}

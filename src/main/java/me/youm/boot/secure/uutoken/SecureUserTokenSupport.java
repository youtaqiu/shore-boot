package me.youm.boot.secure.uutoken;

import me.youm.boot.constant.SecurityConstant;
import me.youm.boot.constant.TokenConstant;
import me.youm.boot.context.BeanContext;
import me.youm.boot.secure.services.SecureUser;
import me.youm.boot.tools.core.PatternUtil;
import me.youm.boot.tools.core.ServletUtil;
import me.youm.boot.web.domain.Result;
import me.youm.boot.web.domain.ResultCode;
import me.youm.boot.web.exception.auth.token.TokenExpiredException;
import me.youm.boot.web.exception.auth.token.TokenValidationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Token Filter 主要增加 Token 的验证
 *
 * @author youta
 */
public class SecureUserTokenSupport extends OncePerRequestFilter {

    private final SecureUserTokenService customUserDetailsTokenService;

    public SecureUserTokenSupport() {
        // Secure Details
        this.customUserDetailsTokenService = BeanContext.getBean("secureUserTokenService", SecureUserTokenService.class);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(TokenConstant.TOKEN_HEADER);
        String tokenHeaderKey = request.getHeader(TokenConstant.TOKEN_HEADER_KEY);
        // token param verify empty
        if (tokenHeader == null) {
            ServletUtil.writeJson(Result.failure(ResultCode.TOKEN_MISSION));
            return;
        }
        // token verify
        SecureUser secureUser;
        try {
            SecureUserToken userToken = customUserDetailsTokenService.verifyToken(tokenHeaderKey, tokenHeader.replaceFirst(TokenConstant.TOKEN_PREFIX, ""));
            secureUser = userToken.getSecureUser();
        } catch (TokenValidationException e) {
            ServletUtil.writeJson(Result.failure(ResultCode.TOKEN_INVALID));
            return;
        } catch (TokenExpiredException e) {
            ServletUtil.writeJson(Result.failure(ResultCode.TOKEN_EXPIRED));
            return;
        }
        // return UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(secureUser, secureUser.getId(), secureUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }


    // TODO 修正匹配策略
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        List<String> pattern = Arrays.asList(SecurityConstant.HTTP_ACT_MATCHERS.split(","));
        return PatternUtil.matches(pattern, ServletUtil.getRequestURI());
    }
}

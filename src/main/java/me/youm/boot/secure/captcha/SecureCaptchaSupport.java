package me.youm.boot.secure.captcha;

import me.youm.boot.constant.SecurityConstant;
import me.youm.boot.context.BeanContext;
import me.youm.boot.tools.core.ServletUtil;
import me.youm.boot.web.domain.Result;
import me.youm.boot.web.domain.ResultCode;
import me.youm.boot.web.exception.auth.captcha.CaptchaExpiredException;
import me.youm.boot.web.exception.auth.captcha.CaptchaValidationException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Captcha Filter 主要增加 Token 的验证
 *
 * @author youta
 */
public class SecureCaptchaSupport extends OncePerRequestFilter {

    public static final String CAPTCHA_KEY = "captchaKey";
    public static final String CAPTCHA_CODE = "captchaCode";

    private final SecureCaptchaService customCaptchaService;


    public SecureCaptchaSupport() {
        this.customCaptchaService = BeanContext.getBean("secureCaptchaService", SecureCaptchaService.class);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String captchaKey = ServletUtil.getParameter(CAPTCHA_KEY);
        String captchaCode = ServletUtil.getParameter(CAPTCHA_CODE);
        // captcha param verify empty
        if (Strings.isBlank(captchaKey)) {
            ServletUtil.writeJson(Result.failure(ResultCode.CAPTCHA_KEY_MISSION));
            return;
        }
        if (Strings.isBlank(captchaCode)) {
            ServletUtil.writeJson(Result.failure(ResultCode.CAPTCHA_CODE_MISSION));
            return;
        }
        // captcha verify
        try {
            customCaptchaService.verifyCaptcha(captchaKey, captchaCode);
        } catch (CaptchaExpiredException e) {
            ServletUtil.writeJson(Result.failure(ResultCode.CAPTCHA_EXPIRED));
            return;
        } catch (CaptchaValidationException e) {
            ServletUtil.writeJson(Result.failure(ResultCode.CAPTCHA_INVALID));
            return;
        }
        customCaptchaService.destroyCaptcha(captchaKey);
        chain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if (SecurityConstant.IS_CAPTCHA_VERIFICATION) {
            return !request.getRequestURI().equals(SecurityConstant.LOGIN_URL) || !request.getMethod().equals(SecurityConstant.LOGIN_METHOD);
        }
        return true;
    }
}

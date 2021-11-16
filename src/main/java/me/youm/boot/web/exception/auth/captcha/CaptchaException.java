package me.youm.boot.web.exception.auth.captcha;

import org.springframework.security.core.AuthenticationException;

/**
 * 验 证 码 异 常 基 类
 *
 * @author youta
 */
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String message) {
        super(message);
    }

}

package me.youm.boot.web.exception.auth.captcha;

/**
 * 验 证 码 失 效 异 常
 *
 * @author youta
 */
public class CaptchaValidationException extends CaptchaException {

    public CaptchaValidationException(String message) {
        super(message);
    }
}

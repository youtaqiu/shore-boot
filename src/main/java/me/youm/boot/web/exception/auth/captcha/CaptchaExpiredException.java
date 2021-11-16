package me.youm.boot.web.exception.auth.captcha;

/**
 * 验 证 码 过 期 异 常
 *
 * @author youta
 */
public class CaptchaExpiredException extends CaptchaException {

    public CaptchaExpiredException(String message) {
        super(message);
    }
}

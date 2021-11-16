package me.youm.boot.web.exception.auth.token;

/**
 * TOKEN 验 证 异 常
 *
 * @author youta
 */
public class TokenValidationException extends TokenException {

    public TokenValidationException(String message) {
        super(message);
    }
}

package me.youm.boot.web.exception.auth.token;

/**
 * TOKEN 过 期 异 常
 *
 * @author youta
 */
public class TokenExpiredException extends TokenException {

    public TokenExpiredException(String message) {
        super(message);
    }
}

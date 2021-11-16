package me.youm.boot.web.exception.auth.token;

import org.springframework.security.core.AuthenticationException;

/**
 * TOKEN 异 常 类
 *
 * @author youta
 */
public class TokenException extends AuthenticationException {

    public TokenException(String message) {
        super(message);
    }
}

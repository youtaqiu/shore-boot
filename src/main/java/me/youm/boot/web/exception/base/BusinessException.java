package me.youm.boot.web.exception.base;

/**
 * 业 务 异 常
 *
 * @author youta
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}

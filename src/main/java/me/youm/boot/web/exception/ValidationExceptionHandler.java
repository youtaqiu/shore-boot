package me.youm.boot.web.exception;

import me.youm.boot.web.domain.Result;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author youta
 */
@Order(1)
@RestControllerAdvice
public class ValidationExceptionHandler {

    /**
     * 处 理 form data 方 式 调 用 接 口 校 验 失 败 抛 出 的 异 常
     * @param e exception
     * @return {@link Result}
     */
    @ExceptionHandler(BindException.class)
    public Result<?> bindExceptionHandler(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return Result.failure(400, "Bad Request", collect);
    }

    /**
     * 处 理 json 请 求 体 调 用 接 口 校 验 失 败 抛 出 的 异 常
     * @param e exception
     * @return {@link Result}
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return Result.failure(400, "Bad Request", collect);
    }

    /**
     * 处 理 单 个 参 数 校 验 失 败 抛 出 的 异 常
     * @param e exception
     * @return {@link Result}
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<?> constraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> collect = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        return Result.failure(400, "Bad Request", collect);
    }
}

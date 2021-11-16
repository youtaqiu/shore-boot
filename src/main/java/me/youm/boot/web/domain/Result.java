package me.youm.boot.web.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Ajax 响 应 实 体
 *
 * @author youta
 */
@Data
@Accessors(chain = true)
public class Result<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 返回结果
     */
    private boolean success;

    /**
     * 携带数据
     */
    private Object data;

    /**
     * 时间戳
     */
    private long timeStamp = System.currentTimeMillis();

    /**
     * 权鉴
     */
    private String tokenKey;

    private String token;

    /**
     * 成 功 操 作
     * @param <T> type
     * @return result
     */
    public static <T> Result<T> success() {
        return success("");
    }

    /**
     * 成 功 操 作 , 携 带 数 据
     * @param <T> type
     * @param data data
     * @return result
     */
    public static <T> Result<T> success(T data) {
        return success(ResultCode.SUCCESS.message(), data);
    }

    /**
     * 成 功 操 作, 携 带 消 息
     * @param <T> type
     * @param message message
     * @return result
     */
    public static <T> Result<T> success(String message) {
        return success(message, null);
    }

    /**
     * 成 功 操 作, 携 带 消 息 和 携 带 数 据
     * @param <T> type
     * @param message message
     * @param data data
     * @return result
     */
    public static <T> Result<T> success(String message, T data) {
        return success(ResultCode.SUCCESS.code(), message, data);
    }

    /**
     * 成 功 操 作, 携 带 自 定 义 状 态 码 和 消 息
     * @param <T> type
     * @param resultCode result code
     * @return result
     */
    public static <T> Result<T> success(ResultCode resultCode) {
        return success(resultCode.code(), resultCode.message());
    }

    /**
     * 成 功 操 作, 携 带 自 定 义 状 态 码 和 消 息
     * @param <T> type
     * @param resultCode resultCode
     * @param tokenKey token key
     * @param token token
     * @return result
     */
    public static <T> Result<T> success(ResultCode resultCode, String tokenKey, String token) {
        Result<T> result = success(resultCode.code(), resultCode.message());
        result.setTokenKey(tokenKey);
        result.setToken(token);
        return result;
    }

    public static <T> Result<T> success(int code, String message) {
        return success(code, message, null);
    }

    /**
     * 成 功 操 作, 携 带 自 定义 状 态 码, 消 息 和 数 据
     * @param <T> type
     * @param code code
     * @param message message
     * @param data data
     * @return type
     */
    public static <T> Result<T> success(int code, String message, T data) {
        return result(code, message, data, true);
    }

    /**
     * 失 败 操 作, 默 认 数 据
     * @param <T> type
     * @return type
     */
    public static <T> Result<T> failure() {
        return failure(ResultCode.FAILURE.message());
    }

    /**
     * 失 败 操 作, 携 带 自 定 义 消 息
     * @param <T> type
     * @param message message
     * @return type
     */
    public static <T> Result<T> failure(String message) {
        return failure(message, null);
    }

    /**
     * 失 败 操 作 , 携 带 自 定 义 消 息 , 状 态 码
     * @param resultCode result code
     * @param <T> type
     * @return type
     */
    public static <T> Result<T> failure(ResultCode resultCode) {
        return failure(resultCode.code(), resultCode.message());
    }

    /**
     * 失 败 操 作, 携 带 自 定 义 消 息 和 数 据
     * @param message msg
     * @param <T> type
     * @param data data
     * @return type
     */
    public static <T> Result<T> failure(String message, T data) {
        return failure(ResultCode.FAILURE.code(), message, data);
    }

    /**
     * 失 败 操 作, 携 带 自 定 义 状 态 码 和 自 定 义 消 息
     * @param <T> type
     * @param code code
     * @param message msg
     * @return type
     */
    public static <T> Result<T> failure(int code, String message) {
        return failure(code, message, null);
    }

    /**
     * 失 败 操 作, 携 带 自 定 义 状 态 码 , 消 息 和 数 据
     * @return type
     * @param <T> type
     * @param code code
     * @param message msg
     * @param data data
     */
    public static <T> Result<T> failure(int code, String message, T data) {
        return result(code, message, data, false);
    }

    /**
     * Boolean 返 回 操 作, 携 带 默 认 返 回 值
     * @param b bool
     * @param <T> type
     * @return ty
     */
    public static <T> Result<T> auto(boolean b) {
        return auto(b, ResultCode.SUCCESS.message(), ResultCode.FAILURE.message());
    }

    /**
     * Boolean 返 回 操 作, 携 带 自 定 义 消 息
     * @param <T> type
     * @param b bool
     * @param success success
     * @param failure failure
     * @return ty
     */
    public static <T> Result<T> auto(boolean b, String success, String failure) {
        if (b) {
            return success(success);
        } else {
            return failure(failure);
        }
    }

    /**
     * Result 构 建 方 法
     * @param <T> type
     * @param code code
     * @param message message
     * @param data data
     * @param success success
     * @return ty
     */
    public static <T> Result<T> result(int code, String message, T data, boolean success) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMsg(message);
        result.setSuccess(success);
        result.setTimeStamp(System.currentTimeMillis());
        result.setData(data);
        return result;
    }

}

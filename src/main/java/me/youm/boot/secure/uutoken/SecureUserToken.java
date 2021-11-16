package me.youm.boot.secure.uutoken;

import lombok.Data;
import me.youm.boot.secure.services.SecureUser;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户 Token 封装实体
 *
 * @author youta
 */
@Data
public class SecureUserToken implements Serializable {

    private static final long serialVersionUID = 3202081924391437904L;

    /**
     * Token
     */
    private String token;

    /**
     * 基本信息
     */
    private SecureUser secureUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime = LocalDateTime.now();

}

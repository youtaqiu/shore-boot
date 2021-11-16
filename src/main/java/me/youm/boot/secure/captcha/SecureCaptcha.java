package me.youm.boot.secure.captcha;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Captcha 验证码实体
 *
 * @author youta
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecureCaptcha {

    /**
     * 标识
     */
    private String key;

    /**
     * 验证码
     */
    private String code;

    /**
     * 验证图片
     */
    private String image;

}

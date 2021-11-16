package me.youm.boot.secure.captcha;

import com.wf.captcha.SpecCaptcha;
import me.youm.boot.constant.CacheNameConstant;
import me.youm.boot.constant.SecurityConstant;
import me.youm.boot.web.domain.Result;
import me.youm.boot.web.exception.auth.captcha.CaptchaExpiredException;
import me.youm.boot.web.exception.auth.captcha.CaptchaValidationException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Captcha 验证码服务
 *
 * @author youta
 */
@Service
public class SecureCaptchaService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 创 建 Captcha
     * @return {@link SecureCaptcha}
     */
    public SecureCaptcha createCaptcha() {
        SpecCaptcha captcha = new SpecCaptcha(142, 38);
        String key = UUID.randomUUID().toString();
        String code = captcha.text().toLowerCase();
        redisTemplate.opsForValue().set(CacheNameConstant.CAPTCHA_NAME_PREFIX + key, code, SecurityConstant.CAPTCHA_EXPIRATION, TimeUnit.SECONDS);
        return new SecureCaptcha(key, code, captcha.toBase64());
    }

    /**
     * 验 证 Captcha
     * @param key key
     * @param code code
     */
    public void verifyCaptcha(String key, String code) {
        String redisCode = taskCaptcha(key);
        if (redisCode == null) throw new CaptchaExpiredException("captcha expired");
        if (!redisCode.equals(code)) throw new CaptchaValidationException("captcha invalid");
    }

    /**
     * 获 取 Captcha
     * @param key key
     * @return value
     */
    public String taskCaptcha(String key) {
        return redisTemplate.opsForValue().get(CacheNameConstant.CAPTCHA_NAME_PREFIX + key);
    }

    /**
     * 销 毁 Captcha
     * @param key key
     */
    public void destroyCaptcha(String key) {
        redisTemplate.delete(CacheNameConstant.CAPTCHA_NAME_PREFIX + key);
    }
}

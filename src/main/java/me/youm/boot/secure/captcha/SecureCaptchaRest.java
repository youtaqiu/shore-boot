package me.youm.boot.secure.captcha;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.youm.boot.constant.ControllerConstant;
import me.youm.boot.web.domain.Result;
import me.youm.boot.web.domain.ResultController;
import me.youm.boot.web.interceptor.annotation.RateLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Captcha Rest 服务
 *
 * @author youta
 */
@Api(tags = {"验证"})
@RestController
@RequestMapping(ControllerConstant.PREFIX + "captcha")
public class SecureCaptchaRest extends ResultController {

    @Resource
    private SecureCaptchaService customCaptchaService;

    /**
     * Captcha 生成
     * @return {@link Result}
     */
    @RateLimit
    @GetMapping("create")
    @ApiOperation(value = "创建验证码")
    public Result<?> createCaptcha() {
        return success(customCaptchaService.createCaptcha());
    }

}

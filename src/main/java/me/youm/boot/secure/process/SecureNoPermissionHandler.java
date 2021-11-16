package me.youm.boot.secure.process;

import me.youm.boot.tools.core.ServletUtil;
import me.youm.boot.web.domain.Result;
import me.youm.boot.web.domain.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Security 用户暂无权限处理类
 *
 * @author youta
 */
@Component
public class SecureNoPermissionHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        ServletUtil.writeJson(Result.failure(ResultCode.NOT_PERMISSION));
    }
}

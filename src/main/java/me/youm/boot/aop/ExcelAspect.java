package me.youm.boot.aop;

import me.youm.boot.aop.annotation.Excel;
import me.youm.boot.aop.enums.Model;
import me.youm.boot.tools.core.ExcelUtil;
import me.youm.boot.tools.core.ServletUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel 导出 Aop
 *
 * @author youta
 */
@Aspect
@Component
public class ExcelAspect {

    /**
     * 切 面 编 程
     */
    @Pointcut("@annotation(me.youm.boot.aop.annotation.Excel) || @within(me.youm.boot.aop.annotation.Excel)")
    public void dsPointCut() {
    }

    /**
     * 处 理 系 统 日 志
     */
    @Around("dsPointCut()")
    private Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object data = null;

        Excel annotation = getAnnotation(joinPoint);
        Class clazz = annotation.clazz();
        Model model = annotation.model();
        HttpServletResponse response = ServletUtil.getResponse();

        try {

            if (model.equals(Model.READ)) {

                // TODO Excel 导入
            }

            data = joinPoint.proceed();

            if (model.equals(Model.WRITE)) ExcelUtil.write(response, clazz, toList(data, clazz));

        } catch (Exception e) {
            // 堆 栈 信 息
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 获 取 注 解
     */
    public Excel getAnnotation(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<?> targetClass = point.getTarget().getClass();
        Excel targetExcel = targetClass.getAnnotation(Excel.class);
        if (targetExcel != null) {
            return targetExcel;
        } else {
            Method method = signature.getMethod();
            return method.getAnnotation(Excel.class);
        }
    }

    /**
     * List 转换
     */
    public static <T> List<T> toList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }
}

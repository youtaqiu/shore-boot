package me.youm.boot.web.interceptor;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import me.youm.boot.context.UserContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 字 段 填 充 拦 截 器
 * @author youta
 * */
@Component
public class DomainInterceptor implements MetaObjectHandler {

    @Resource
    private UserContext userContext;

    @Override
    public void insertFill(MetaObject metaObject) {
        createField(metaObject);
        updateField(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        updateField(metaObject);
    }

    /**
     * @param metaObject metaObject
     * */
    public void createField(MetaObject metaObject){
        this.strictInsertFill(metaObject,"createBy", String.class, userContext.getUserId());
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
    }

    /**
     * @param metaObject metaObject
     * */
    public void updateField(MetaObject metaObject){
        this.strictInsertFill(metaObject,"updateBy", String.class, userContext.getUserId());
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }

}

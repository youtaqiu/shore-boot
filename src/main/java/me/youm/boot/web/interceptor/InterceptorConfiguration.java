package me.youm.boot.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import me.youm.boot.constant.TenantConstant;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author youta
 */
@Slf4j
@Configuration
public class InterceptorConfiguration implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.addMyInterceptor();
    }

    private void addMyInterceptor() {
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            if(TenantConstant.enable) {
                log.info("Enable multi tenant");
                sqlSessionFactory.getConfiguration().addInterceptor(new TenantInterceptor());
            }
            sqlSessionFactory.getConfiguration().addInterceptor(new DataScopeInterceptor());
        }
    }
}

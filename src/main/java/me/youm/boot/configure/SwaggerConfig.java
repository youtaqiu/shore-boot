package me.youm.boot.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * Swagger 文档配置
 *
 * @author youta
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String BASE_PACKAGE = "me.youm";

    private static final String DEFAULT_GROUP = "default";

    private static final Boolean ENABLE = true;

    @Bean
    public Docket docker() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName(DEFAULT_GROUP)
                .enable(ENABLE)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Shore API",
                "Shore For Spring Boot",
                "v1",
                "https://.youm.me", new Contact("xiyu", "https://github.com/youtaqiu", "youtaqiu@gmail.com"),
                "apache license",
                "https://github.com/youtaqiu/shore-boot",
                new ArrayList<>()
        );
    }

}

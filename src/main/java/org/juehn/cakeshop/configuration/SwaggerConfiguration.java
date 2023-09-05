package org.juehn.cakeshop.configuration;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 自定义 Swagger 接口文档的配置
 *
 * @author ciaociao
 */


@Configuration//该注解是Springfox-swagger框架提供的使用Swagger注解，该注解必须加
@EnableSwagger2//knife4j提供的增强扫描注解,Ui提供了例如动态参数、参数过滤、接口排序等增强功能
@EnableKnife4j
public class SwaggerConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 这里一定要标注你控制器的位置
                .apis(RequestHandlerSelectors.basePackage("org.juehn.cakeshop.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * api 信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ciaociao后台接口管理")
                .description("接口文档")
                .termsOfServiceUrl("https://github.com/ClA0ClA0")
                .contact(new Contact("ciaociao","https://github.com/ClA0ClA0","1578460248@qq.com"))
                .version("1.0")
                .build();
    }
}

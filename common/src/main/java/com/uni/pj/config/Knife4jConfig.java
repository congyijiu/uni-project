package com.uni.pj.config;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * knife4j配置信息
 */
@Configuration
public class Knife4jConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("uniproject") // 标题
                        .description(" uniprojectAPI文档") // 描述
                        .version("v1") // 版本
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))) // 许可证信息
                        .externalDocs(new ExternalDocumentation() // 文档配置
                        .description("外部文档") // 描述
                        .url("https://congyijiu.wenlingyun.com/doc.html")); // 文档URL
    }


}
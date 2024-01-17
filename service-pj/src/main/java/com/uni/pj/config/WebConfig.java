package com.uni.pj.config;

import com.uni.pj.interceptor.UniInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author congyijiu
 * @create 2023-11-25-13:14
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 对swagger的请求不进行拦截
        String[] excludePatterns = new String[]{"/user/login", "/user/register","/swagger-resources/**", "/webjars/**", "/v3/**", "/swagger-ui.html/**",
                "/api", "/api-docs", "/api-docs/**", "/doc.html/**","/error/**","/admin/system/index/login"};
        registry.addInterceptor(new UniInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludePatterns);
    }


    @Override
    public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
        registry.addMapping("/**") // 允许跨域的路径
                .allowedOriginPatterns("*") // 允许跨域的域名，可以用*表示允许任何域名使用
                .allowCredentials(true) // 允许使用cookie
                .allowedMethods("GET", "POST", "DELETE", "PUT") // 允许所有方法
                .allowedHeaders("*") // 允许携带任何头信息
                .maxAge(3600); // 跨域允许时间
    }
}
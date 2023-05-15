package com.web.upload.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Lcp
 * @version 1.0
 * @date 2023/5/11 15:10
 */

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域访问的路径
        registry.addMapping("/api/**")
                // 允许跨域访问的源
                .allowedOrigins("*")
                // 允许请求方法
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                // 预检间隔时间
                .maxAge(168000)
                // 允许头部设置
                .allowedHeaders("*");
    }
}

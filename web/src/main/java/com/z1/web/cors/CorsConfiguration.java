package com.z1.web.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web 跨域配置类
 * @author dean Huang
 * @since 0.0.1
 *
 */

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
        	.addMapping("/**")
        	.allowedOrigins("*")
        	.allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
        	.allowCredentials(false)
        	.maxAge(5000);
    }
	
}


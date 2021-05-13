package com.z1.web.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web 权限验证配置类
 * @author dean Huang
 * @since 0.0.1
 *
 */

@EnableConfigurationProperties(AuthProperties.class)
@Configuration
public class AuthInterceptorConfiguration implements WebMvcConfigurer {
	
	@ConditionalOnMissingBean
	@Bean
	public AuthHandler authHandler() {
		return new DefaultAuthHandler();
	}
	
	@Autowired
	private AuthHandler authHandler;
	@Autowired
	private AuthProperties authProperties;
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		
		if (null != authProperties && authProperties.isIntercept()) {
			List<String> excludePathPatterns = new ArrayList<>(Arrays.asList(
				//Swagger
				"/swagger**/**", "/webjars/**", "/v3/**", "/doc.html"
				//Login
				,"/login"
			));
			excludePathPatterns.addAll(authProperties.getIgnoreUrls());
			registry.addInterceptor(Interceptor())
			.excludePathPatterns(excludePathPatterns)
			.addPathPatterns("/**");
		}
		
    }
	
    private HandlerInterceptor Interceptor() {
        return new HandlerInterceptor() {
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {
				//如果不是映射到方法直接通过
				if (handler instanceof HandlerMethod) {
					return authHandler.handle(request, response, handler, authProperties);
				}
				return true;
			}
        };
    }
	
}
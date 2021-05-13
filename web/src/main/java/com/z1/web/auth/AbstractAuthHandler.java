package com.z1.web.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;

import com.z1.web.exception.TokenException;

import lombok.extern.slf4j.Slf4j;

/**
 * Web 权限验证处理抽象类
 * @author dean Huang
 * @since 0.0.1
 *
 */

@Slf4j
public abstract class AbstractAuthHandler implements AuthHandler {

	@Override
	public boolean handle(HttpServletRequest request, HttpServletResponse response, Object handler,
			AuthProperties authProperties) {
		// 如果不是映射到方法直接通过
		if (!(handler instanceof HandlerMethod)) {
            return true;
		}
		
		// 判断是否存在令牌信息，如果存在，则允许登录
		String token = request.getHeader(authProperties.getTokenHead());
		if (StringUtils.isBlank(token)) {
			log.error(request.getRequestURI());
			throw new TokenException("无Token，请重新登录");
		}
		
		return this.verifyToken(request, response, token);
	}
	
	protected abstract boolean verifyToken(
		HttpServletRequest request, HttpServletResponse response, 
		String token
	);
	
}

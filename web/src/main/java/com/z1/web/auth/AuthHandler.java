package com.z1.web.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Web 权限验证处理接口
 * @author dean Huang
 * @since 0.0.1
 *
 */

public interface AuthHandler {

	boolean handle(HttpServletRequest request, HttpServletResponse response, Object handler, AuthProperties authProperties);
	
}

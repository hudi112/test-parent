package com.z1.web.auth;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.z1.web.Const;
import com.z1.web.util.TokenUtil;

/**
 * Web 默认权限验证处理类
 * @author dean Huang
 * @since 0.0.1
 *
 */

public class DefaultAuthHandler extends AbstractAuthHandler {
	
	@Override
	protected boolean verifyToken(HttpServletRequest request, HttpServletResponse response, String token) {
		Map<String, String> claimMap = TokenUtil.verifyToken(token);
		if (null != claimMap) {
			request.setAttribute(Const.REQUEST_USER, claimMap);
			return true;
		}
		return false;
	}

}

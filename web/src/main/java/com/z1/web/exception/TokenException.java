package com.z1.web.exception;

/**
 * Web Token异常类
 * @author dean Huang
 * @since 0.0.1
 *
 */

public class TokenException extends RuntimeException {
	
	private static final long serialVersionUID = -6672496168398922442L;
	
	public TokenException(String message) {
		super(message);
    }
	
	public TokenException(String message, Throwable e) {
		super(message, e);
    }

}

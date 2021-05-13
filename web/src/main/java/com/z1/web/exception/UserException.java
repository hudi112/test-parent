package com.z1.web.exception;

/**
 * Web 用户异常类
 * @author dean Huang
 * @since 0.0.1
 *
 */

public class UserException extends RuntimeException {
	
	private static final long serialVersionUID = -6672496168398922442L;
	
	public UserException(String message) {
		super(message);
    }
	
	public UserException(String message, Throwable e) {
		super(message, e);
    }

}

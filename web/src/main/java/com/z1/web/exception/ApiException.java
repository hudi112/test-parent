package com.z1.web.exception;

/**
 * WebApi异常类
 * @author dean Huang
 * @since 0.0.1
 *
 */

public class ApiException extends RuntimeException{
	
	private static final long serialVersionUID = -6672496168398922442L;
	
	public ApiException(String message) {
		super(message);
    }
	
	public ApiException(Throwable e) {
		super(e);
	}

	public ApiException(String message, Throwable cause) {
		super(message, cause);
	}

}

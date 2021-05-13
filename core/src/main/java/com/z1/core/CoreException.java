package com.z1.core;

/**
 * 基础异常类
 * @author dean Huang
 * @since 0.0.1
 *
 */

public class CoreException extends RuntimeException{
	
	private static final long serialVersionUID = -6672496168398922442L;
	
	public CoreException(String message) {
		super(message);
    }
	
	public CoreException(Throwable e) {
		super(e);
	}

	public CoreException(String message, Throwable cause) {
		super(message, cause);
	}

}

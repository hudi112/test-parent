package com.z1.web.model;

/**
 * WebApi异常响应类
 * @author dean Huang
 * @since 0.0.1
 *
 */

public class ApiErrorResponse extends R<Object> {
	
	private static final long serialVersionUID = -4110967301982607497L;

	public ApiErrorResponse(int code, String msg) {
		super();
		this.setCode(code);
		this.setMsg(msg);
	}
	
	public ApiErrorResponse() {
		this(FinalCode.REQUEST_FAILED, null);
	}
	
	public ApiErrorResponse(String msg) {
		this(FinalCode.REQUEST_FAILED, msg);
	}
	
	
	
}

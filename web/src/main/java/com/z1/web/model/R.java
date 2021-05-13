package com.z1.web.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

/**
 * Web响应结果对象
 * @author dean Huang
 * @since 0.0.1
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
@Setter
@Getter
public class R<T> implements Serializable {
	
	private static final long serialVersionUID = -1581392669077986558L;
	
	private int code;
	private String msg;
	private T data;
	
	public R() { }
	public R(Builder<T> builder) {
		this.setCode(builder.code);
		this.setMsg(builder.msg);
		this.setData(builder.data);
	} 
	
	public static class Builder<T> {
		private int code;
		private String msg;
		private T data;
		
		public Builder() { }
		public Builder<T> code(int code) {
			this.code = code;
			return this;
		}
		public Builder<T> data(T data) {
			this.data = data;
			return this;
		}
		public Builder<T> msg(String msg) {
			this.msg = msg;
			return this;
		}
		public Builder<T> success(T data) {
			 this.data(data)
				.msg("请求成功!")
				.code(FinalCode.REQUEST_SUCCESS);
			 return this;
		}
		public Builder<T> failed(String msg) {
			return this.msg(msg)
				.code(FinalCode.REQUEST_FAILED);
		}
		public R<T> build() {
			return new R<T>(this);
		}
	}
	
}

package com.z1.web;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.z1.core.CoreException;
import com.z1.web.exception.ApiException;
import com.z1.web.exception.TokenException;
import com.z1.web.exception.UserException;
import com.z1.web.model.ApiErrorResponse;
import com.z1.web.model.FinalCode;

import lombok.extern.slf4j.Slf4j;

/**
 * Web基础接口类
 * @author dean Huang
 * @since 0.0.1
 *
 */

@Slf4j
public abstract class CoreController {
	
	/**
	 * 参数校验异常
	 * @param apiException
	 * @return
	 */
	@ExceptionHandler(BindException.class)
	public ApiErrorResponse bindException(BindException bindException) {
		String message = bindException.getBindingResult().getFieldError().getDefaultMessage();
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse(message);
		if (log.isDebugEnabled()) {
			log.warn(message, bindException);
		}
		else {
			log.warn(message);
		}
        return  apiErrorResponse;
    } 
	
	/**
	 * 方法参数验证失败异常
	 * @param methodArgumentNotValidException
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ApiErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
		String message = methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage();
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse(message);
		if (log.isDebugEnabled()) {
			log.warn(message, methodArgumentNotValidException);
		}
		else {
			log.warn(message);
		}
        return  apiErrorResponse;
	}
	
	@ExceptionHandler(CoreException.class)
    public ApiErrorResponse apiException(CoreException coreException) {
		String message = coreException.getMessage();
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse(message);
		if (log.isDebugEnabled()) {
			log.warn(message, coreException);
		}
		else {
			log.warn(message);
		}
        return  apiErrorResponse;
    }
	
	/**
	 * Api异常
	 * @param apiException
	 * @return
	 */
	@ExceptionHandler(ApiException.class)
    public ApiErrorResponse apiException(ApiException apiException) {
		String message = apiException.getMessage();
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse(message);
		if (log.isDebugEnabled()) {
			log.warn(message, apiException);
		}
		else {
			log.warn(message);
		}
        return  apiErrorResponse;
    }
	
	/**
	 * Token异常
	 * @param tokenException
	 * @return
	 */
	@ExceptionHandler(TokenException.class)
    public ApiErrorResponse tokenException(TokenException tokenException) {
		String message = tokenException.getMessage();
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse(FinalCode.ACCOUNT_EXPIRED, message);
		if (log.isDebugEnabled()) {
			log.warn(message, tokenException);
		}
		else {
			log.warn(message);
		}
        return apiErrorResponse;
    }
	
	/**
	 * 用户异常
	 * @param userException
	 * @return
	 */
	@ExceptionHandler(UserException.class)
    public ApiErrorResponse userException(UserException userException) {
		String message = userException.getMessage();
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse(FinalCode.ACCOUNT_INVALID, message);
		if (log.isDebugEnabled()) {
			log.warn(message, userException);
		}
		else {
			log.warn(message);
		}
        return apiErrorResponse;
    }
	
	/**
	 * Http消息不可读异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiErrorResponse httpMessageNotReadableException(HttpMessageNotReadableException e) {
		String message="错误原因：1.没有请求体；2.请求体中的参数类型不匹配";
		ApiErrorResponse apiErrorResponse;
		if (log.isDebugEnabled()) {
			apiErrorResponse = new ApiErrorResponse(message+" ("+e.getMessage()+")");
			log.warn(message, e);
		}
		else {
			apiErrorResponse = new ApiErrorResponse(message);
			log.warn(message);
		}
        return  apiErrorResponse;
    }
	
	/**
	 * 出现此错误，后端开发应该修改代码处理此异常，处理抛出ApiException或者其自定义的子类
     * @ExceptionHandler标记的方法返必须为ApiErrorResponse及其自定义子类
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ApiErrorResponse exception(Exception e) {
    	ApiErrorResponse apiErrorResponse;
    	log.error("注意: 出现此错误，后端开发应该修改代码处理此异常，处理抛出ApiException或者其自定义的子类");
    	if (log.isDebugEnabled()) {
    		String message="注意: 出现此错误，后端开发应该修改代码处理此异常，处理抛出ApiException或者其自定义的子类"+" ("+e.getMessage()+")";
			apiErrorResponse = new ApiErrorResponse(message);
			log.error(e.getMessage(), e);
		}
		else {
			String message="服务运行出错了,请联系服务提供商解决此问题";
			apiErrorResponse = new ApiErrorResponse(message);
			log.error(e.getMessage(), e);
		}
        return apiErrorResponse;
    }
}

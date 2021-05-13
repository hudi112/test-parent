package com.z1.web.model;

/**
 * Web响应状态码
 * @author dean Huang
 * @since 0.0.1
 *
 */

public final class FinalCode {
	
	/**
	 * 请求成功
	 */
	public static final int REQUEST_SUCCESS = 0;
	/**
	 * 无数据
	 */
	public static final int NO_DATA = 30000;
	/**
	 * 请求失败
	 */
	public static final int REQUEST_FAILED = 30001;
	/**
	 * 账号失效
	 */
	public static final int ACCOUNT_INVALID = 30002;
	/**
	 * 账号过期
	 */
	public static final int ACCOUNT_EXPIRED = 30003;
	/**
	 * 访问频率过快
	 */
	public static final int ACCESS_FAST_FREQ = 30004;
	/**
	 * 账户锁定
	 */
	public static final int ACCOUNT_LOCKED = 30005;
	/**
	 * 账号信息有误
	 */
	public static final int ACCOUNT_ERROR = 30006;
	/**
	 * URL不存在
	 */
	public static final int URL_NOT_FOUND = 30007;
	/**
	 * 无权限访问此API
	 */
	public static final int NO_ACCESS = 30008;

}

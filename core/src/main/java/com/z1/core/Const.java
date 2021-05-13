package com.z1.core;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 常量接口
 * @author dean Huang
 * @since 0.0.1
 *
 */

public interface Const {

	/**
	 * 禁用
	 */
	final int FALSE = 0;
	/**
	 * 启用
	 */
	final int TRUE = 1;
	
	/**
	 * 批量操作数量
	 */
	final int BATCH_SIZE = 5000;
	
	/**
	 * 字符集
	 */
	final Charset UTF8_CHARSET = StandardCharsets.UTF_8;
	
}

package com.z1.web;

/**
 * Web常量接口
 * @author dean Huang
 * @since 0.0.1
 *
 */

public interface Const extends com.z1.core.Const {

	/**
	 * USER
	 */
	final String REQUEST_USER = "user";
	/**
	 * TOKEN
	 */
	final String REQUEST_TOKEN = "sic-token";
	
	/**
	 * 顶级节点编号
	 */
	final String TOP_NODE_ID = "#sic#";
	
	/**
	 * 默认页码
	 */
	final long PAGE_NUM_DEFAULT = 1;
	/**
	 * 每页默认数据量
	 */
	final long PAGE_SIZE_DEFAULT = 10;
	
}

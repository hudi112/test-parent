package com.z1.runner;

import cn.hutool.core.codec.Base64;

public interface Const extends com.z1.web.Const {

	/**
	 * 默认密码
	 */
	final String PASSWORD_DEF = Base64.encode("runner");
	
	/**
	 * 编号间隔
	 */
	final String ID_SEPARATOR = "_";
	
	/**
	 * 工作编号前缀
	 */
	final String JOB_ID_PREFIX = "JOB" + ID_SEPARATOR;
	
}

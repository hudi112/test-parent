package com.z1.runner.job;

public interface Job {

	/**
	 * 任务名称
	 * @return
	 */
	JobType jobType();
	
	/**
	 * 初始化
	 */
	void init();
	
	/**
	 * 处理前
	 */
	void postProcessBefore();
	
	/**
	 * 处理
	 */
	void postProcess();
	
	/**
	 * 处理后
	 */
	void postProcessAfter();
	
}

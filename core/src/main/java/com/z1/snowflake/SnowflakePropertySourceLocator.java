package com.z1.snowflake;

/**
 * 雪花算法配置Load接口
 * @author dean Huang
 * @since 0.0.1
 *
 */

public interface SnowflakePropertySourceLocator {

	void loadSnowflakePropertySource(SnowflakeProperties properties);
	
}

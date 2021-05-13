package com.z1.snowflake;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * 雪花算法参数类
 * @author dean Huang
 * @since 0.0.1
 *
 */

@Setter
@Getter
@ConfigurationProperties(prefix = "snowflake")
public class SnowflakeProperties {

	/**
	 * 服务器编号
	 */
	private long workerId = 0L;
	
	/**
	 * 中心编号
	 */
	private long centerId = 0L;
	
}

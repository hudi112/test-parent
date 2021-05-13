package com.z1.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.z1.snowflake.SnowflakeConfiguration;
import com.z1.util.SpringContextHolder;

/**
 * 基础配置类
 * @author dean Huang
 * @since 0.0.1
 *
 */

@Configuration
@Import({
	SpringContextHolder.class, 
	SnowflakeConfiguration.class
})
public class CoreConfiguration {

}

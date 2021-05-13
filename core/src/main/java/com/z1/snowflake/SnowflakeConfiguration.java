package com.z1.snowflake;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.hutool.core.lang.generator.SnowflakeGenerator;

/**
 * 雪花算法配置类
 * @author dean Huang
 * @since 0.0.1
 *
 */

@Configuration
@EnableConfigurationProperties(SnowflakeProperties.class)
public class SnowflakeConfiguration {

	@Bean
	public SnowflakeGenerator snowflakeGenerator(SnowflakeProperties properties) {
		return SnowflakeFactory.newSnowflakeGeneratorInstance(properties);
	}

}

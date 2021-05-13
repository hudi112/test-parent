package com.z1.snowflake;

import java.util.List;

import org.springframework.core.io.support.SpringFactoriesLoader;

import com.z1.util.SpringContextHolder;

import cn.hutool.core.lang.generator.SnowflakeGenerator;

/**
 * 雪花算法工厂类
 * @author dean Huang
 * @since 0.0.1
 *
 */

public final class SnowflakeFactory {

	/**
	 * 创建雪花算法实例对象
	 * 若SpringContext中是否存在实例直接返回，反之新建
	 * @return
	 */
	public static final SnowflakeGenerator newSnowflakeGeneratorInstance() {
		SnowflakeProperties properties = SpringContextHolder.getBean(SnowflakeProperties.class);
		if (null == properties) {
			properties = new SnowflakeProperties();
		}
		return newSnowflakeGeneratorInstance(properties);
	}
	
	/**
	 * 创建雪花算法实例对象
	 * @param properties 雪花算法配置
	 * @return
	 */
	public static final SnowflakeGenerator newSnowflakeGeneratorInstance(SnowflakeProperties properties) {
		List<SnowflakePropertySourceLocator> locators = SpringFactoriesLoader.loadFactories(
			SnowflakePropertySourceLocator.class, 
			SnowflakeFactory.class.getClassLoader()
		);
		//SourceLocator读取配置
		if (null != locators) {
			locators.forEach(locator -> {
				locator.loadSnowflakePropertySource(properties);
			});
		}
		return new SnowflakeGenerator(
			properties.getCenterId(), properties.getWorkerId()
		);
	}

}

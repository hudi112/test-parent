package com.z1.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * ApplicationContextAware实现类
 * @author dean Huang
 * @since 0.0.1
 *
 */

@Component
public class SpringContextHolder implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextHolder.applicationContext = applicationContext;
	}
	
	public static final ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public static final <T> T getBean(Class<T> clazz) {
		return null == applicationContext ? null : applicationContext.getBean(clazz);
	}
	
	public static final String getProperty(String key) {
		if (null != applicationContext) {
			Environment environment = applicationContext.getEnvironment();
			if (null != environment) {
				return environment.getProperty(key);
			}
		}
		return null;
	}

}

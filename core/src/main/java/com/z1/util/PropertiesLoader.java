package com.z1.util;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.DefaultPropertiesPersister;

/**
 * Properties Loader工具类
 * @author dean Huang
 * @since 0.0.1
 *
 */

public final class PropertiesLoader {

	/**
	 * 加载指定路径下的Properties配置
	 * @param path
	 * @return
	 */
	public static final Properties load(String path) {
		
		Properties props = new Properties();
		
		Resource resource = new DefaultResourceLoader(PropertiesLoader.class.getClassLoader()).getResource(path);
		if (null != resource) {
			InputStream input = null;
			try {
				input = resource.getInputStream();
				new DefaultPropertiesPersister().load(props, input);
			}
			catch (Throwable e) {
				throw new IllegalArgumentException(e);
			}
			finally {
				if (null != input) {
					try { input.close(); }
					catch (Throwable e) { }
				}
			}
		}
		return props;
	}
	
}

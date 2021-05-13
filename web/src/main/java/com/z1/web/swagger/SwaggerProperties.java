package com.z1.web.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Swagger参数类
 * @author dean Huang
 * @since 0.0.1
 *
 */

@Component
@ConfigurationProperties("swagger")
@Data
public class SwaggerProperties {

	/**
	 * 是否开启Swagger,生产环境建议关闭
	 */
	private Boolean enable = false;
	/**
	 * 项目应用名
	 */
	private String applicationName;
	/**
	 * 项目版本
	 */
	private String applicationVersion;
	/**
	 * 项目描述
	 */
	private String applicationDescription;
	/**
	 * apis包路径
	 */
	private String apisPackage;
	/**
	 * 调试地址
	 */
	private String tryHost;
	/**
	 * 作者
	 */
	private String contactName = "DeanHuang";
	/**
	 * 作者邮箱
	 */
	private String contactEmail = "hudi-112@163.com";
	
}

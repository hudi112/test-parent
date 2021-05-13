package com.z1.web.auth;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Web 权限参数类
 * @author dean Huang
 * @since 0.0.1
 *
 */

@Data
@ConfigurationProperties("auth")
public class AuthProperties {
	
	/**
	 * Token
	 */
	private String tokenHead;
	/**
	 * Head验证
	 */
	private List<String> requiredHeads;
	/**
	 * 忽略地址集
	 */
	private List<String> ignoreUrls = Collections.emptyList();
	
	public boolean isIntercept() {
		return StringUtils.isNotBlank(this.tokenHead)
			|| (null != this.requiredHeads && !this.requiredHeads.isEmpty()); 
	}
	
	public Set<String> getInterceptHeads() {
		Set<String> heads = null;
		//需要验证Heads
		if (null != this.requiredHeads) {
			heads = new HashSet<>(this.requiredHeads);
		}
		//需要验证Token
		if (StringUtils.isNotBlank(this.tokenHead)) {
			if (null == heads) {
				heads = new HashSet<>(Arrays.asList(this.tokenHead));
			}
			else {
				heads.add(this.tokenHead);
			}
		}
		return heads; 
	}
	
}
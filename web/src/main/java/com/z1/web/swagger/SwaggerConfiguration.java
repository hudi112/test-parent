package com.z1.web.swagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.z1.web.auth.AuthProperties;

import io.swagger.models.auth.In;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger配置类
 * @author dean Huang
 * @since 0.0.1
 *
 */

@EnableOpenApi
@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfiguration {

	@Autowired
	private SwaggerProperties swaggerProperties;
	@Autowired(required = false)
	private AuthProperties authProperties;
	
	@Bean
	public Docket buildDocket() {
		return new Docket(DocumentationType.OAS_30)
			.pathMapping("/")
			//定义是否开启Swagger，false为关闭
			.enable(swaggerProperties.getEnable())
			//将api的原信息设置为包含在json ResourceListing的响应中
        	.apiInfo(buildApiInf())
        	//接口调试地址
        	.host(swaggerProperties.getTryHost())
        	//选择那些接口作为Swagger的发布
        	.select()
        	.apis(RequestHandlerSelectors.basePackage(swaggerProperties.getApisPackage()))
        	.paths(PathSelectors.any())
        	.build()
        	//支持的通讯协议集合
        	.protocols(new HashSet<String>(Arrays.asList("https", "http")))
        	//授权信息设置，必要的Header（如：Token等认证信息）
        	.securitySchemes(securitySchemes())
        	//授权信息全局应用
        	.securityContexts(securityContexts());
    }

	/**
	 * 授权信息全局应用
	 * @return
	 */
	private List<SecurityContext> securityContexts() {
		List<SecurityReference> array = new ArrayList<>();
		if (null != authProperties && authProperties.isIntercept()) {
			for (String requiredHead : authProperties.getInterceptHeads()) {
				array.add(new SecurityReference(
					requiredHead, new AuthorizationScope[]{new AuthorizationScope("global", "")})
				);
			}
		}
		return Collections.singletonList(
			SecurityContext.builder()
				.securityReferences(array)
				.build()
        );
	}

	/**
	 * 授权信息
	 * @return
	 */
	private List<SecurityScheme> securitySchemes() {
		if (null != authProperties && authProperties.isIntercept()) {
			List<SecurityScheme> array = new ArrayList<>();
			for (String requiredHead : authProperties.getInterceptHeads()) {
				array.add(new ApiKey(requiredHead, requiredHead, In.HEADER.toValue()));
			}
			return array;
		}
		return Collections.emptyList();
		
	}

	/**
	 * API页面上半部展示信息
	 * @return
	 */
    private ApiInfo buildApiInf(){
        return new ApiInfoBuilder()
        	.title(swaggerProperties.getApplicationName())
        	.description(swaggerProperties.getApplicationDescription())
        	.contact(new Contact(swaggerProperties.getContactName(), null, swaggerProperties.getContactEmail()))
        	.version(swaggerProperties.getApplicationVersion())
        	.build();
	}
	
}

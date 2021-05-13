package com.z1.runner.mybatisplus;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.bind.PlaceholdersResolver;
import org.springframework.boot.context.properties.bind.PropertySourcesPlaceholdersResolver;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * mybatisplus代码生成类
 * @author dean Huang
 * @since 0.0.1
 *
 */

public class MybatisPlusGenerator {

	/**
	 * MybatisPlus配置路径
	 */
	final String MYBATIS_PLUS_CONFIG_LOCATION = "mybatisplus-auto-generator.properties";
	
//	final ResourceLoader resourceLoader = new DefaultResourceLoader(this.getClass().getClassLoader());
//	final PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
	
	//表名
	final String PROPERTY_TABLES = "generator.tables";
	
	//作者
	final String PROPERTY_GLOBAL_AUTHOR = "generator.global.author";
	//输出路径
	final String PROPERTY_GLOBAL_OUTPUT_DIR = "generator.global.output.dir";
	
	//数据库驱动
	final String PROPERTY_DATASOURCE_DRIVER = "generator.datasource.driver";
	//数据库URL
	final String PROPERTY_DATASOURCE_URL = "generator.datasource.url";
	//数据库用户名
	final String PROPERTY_DATASOURCE_USERNAME = "generator.datasource.username";
	//数据库密码
	final String PROPERTY_DATASOURCE_PASSWORD = "generator.datasource.password";
	
	//父包
	final String PROPERTY_PACKAGE_PARENT = "generator.package.parent";
	//实体包
	final String PROPERTY_PACKAGE_ENTITY = "generator.package.entity";
	//Mapper包
	final String PROPERTY_PACKAGE_MAPPER = "generator.package.mapper";
	//业务接口包
	final String PROPERTY_PACKAGE_SERVICE = "generator.package.service";
	//业务实现包
	final String PROPERTY_PACKAGE_SERVICE_IMPL = "generator.package.service.impl";
	//接口包
	final String PROPERTY_PACKAGE_CONTROLLER = "generator.package.controller";
	
	//策略-表前缀
	final String PROPERTY_STRATEGY_TABLE_PREFIX = "generator.strategy.table.prefix";
	//策略-父Controller类
	final String PROPERTY_STRATEGY_SUPER_CONTROLLER_CLASS = "generator.strategy.super.controller.class";
	
	private PlaceholdersResolver placeholdersResolver;
	
//	private Properties loadProperties() {
//		
//		Properties props = new Properties();
//		
//		Resource resource = this.resourceLoader.getResource(MYBATIS_PLUS_CONFIG_LOCATION);
//		if (null != resource) {
//			InputStream input = null;
//			try {
//				input = resource.getInputStream();
//				this.propertiesPersister.load(props, input);
//			}
//			catch (Throwable e) {
//				throw new IllegalArgumentException(e);
//			}
//			finally {
//				if (null != input) {
//					try { input.close(); }
//					catch (Throwable e) { }
//				}
//			}
//		}
//		return props;
//	}
	
	public void generator() throws IOException, ClassNotFoundException {
		
		this.placeholdersResolver = new PropertySourcesPlaceholdersResolver(
			Collections.singletonList(
				new SystemEnvironmentPropertySource("systemEnvironment", getSystemEnvironment())
			)
		);
		
//		Properties props = loadProperties();
		Properties props = PropertiesLoaderUtils.loadAllProperties(MYBATIS_PLUS_CONFIG_LOCATION);
		
		//表名
//		String[] tables = new String[] {
//			"tbl_test"
//		};
		String[] tables = props.getProperty(PROPERTY_TABLES, "").split(",");
		
		//代码生成器
		AutoGenerator generator = new AutoGenerator();
		
		/**=== 全局配置 ===**/
		String globalAuthor = getProperty(props.getProperty(PROPERTY_GLOBAL_AUTHOR, "DeanHuang(hudi-112@163.com)"));
		String globalOutputDir = getProperty(props.getProperty(PROPERTY_GLOBAL_OUTPUT_DIR, System.getProperty("user.dir") + "/src/main/java"));
		GlobalConfig globalConfig = new GlobalConfig();
		globalConfig.setOutputDir(globalOutputDir);
		globalConfig.setAuthor(globalAuthor);
		globalConfig.setIdType(IdType.INPUT);
		globalConfig.setOpen(false);
		globalConfig.setSwagger2(false);//实体属性 Swagger2 注解
		generator.setGlobalConfig(globalConfig);
		
		/**=== 数据源配置 ===**/
		String dataSourceDriverName = props.getProperty(PROPERTY_DATASOURCE_DRIVER, "com.mysql.cj.jdbc.Driver");
		String dataSourceUrl = props.getProperty(PROPERTY_DATASOURCE_URL, "jdbc:mysql://192.168.0.60:3306/dynamic-form?useUnicode=true&characterEncoding=utf-8");
		String dataSourceUserName = props.getProperty(PROPERTY_DATASOURCE_USERNAME, "root");
		String dataSourcePassword = props.getProperty(PROPERTY_DATASOURCE_PASSWORD, "root");
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setDriverName(dataSourceDriverName);
		dataSourceConfig.setUrl(dataSourceUrl);
		dataSourceConfig.setUsername(dataSourceUserName);
		dataSourceConfig.setPassword(dataSourcePassword);
		generator.setDataSource(dataSourceConfig);
		
		/**=== 包配置 ===**/
		String packageParent = props.getProperty(PROPERTY_PACKAGE_PARENT, "com.sic");
		String packageEntity = props.getProperty(PROPERTY_PACKAGE_ENTITY, "entity");
		String packageMapper = props.getProperty(PROPERTY_PACKAGE_MAPPER, "mapper");
		String packageService = props.getProperty(PROPERTY_PACKAGE_SERVICE, "service");
		String packageServiceImpl = props.getProperty(PROPERTY_PACKAGE_SERVICE_IMPL, "service.impl");
		String packageController = props.getProperty(PROPERTY_PACKAGE_CONTROLLER, "controller");
		PackageConfig packageConfig = new PackageConfig();
		packageConfig.setParent(packageParent);
		packageConfig.setEntity(packageEntity);
		packageConfig.setMapper(packageMapper);
		packageConfig.setService(packageService);
		packageConfig.setServiceImpl(packageServiceImpl);
		packageConfig.setController(packageController);
		generator.setPackageInfo(packageConfig);
		
		/**=== 配置模板 ===**/
		TemplateConfig templateConfig = new TemplateConfig();
		templateConfig.setXml(null);
		//代码模板
		//指定自定义模板路径, 位置：/resources/templates/entity2.java.ftl(或者是.vm)
		//不要带上.ftl(或者是.vm), 会根据使用的模板引擎自动识别
//		templateConfig.setEntity("templates/entity2.java");
		generator.setTemplate(templateConfig);
		
		/**=== 策略配置 ===**/
		String strategTablePrefix = props.getProperty(PROPERTY_STRATEGY_TABLE_PREFIX, "");
		String strategSuperControllerClass = props.getProperty(PROPERTY_STRATEGY_SUPER_CONTROLLER_CLASS, "");
		StrategyConfig strategyConfig = new StrategyConfig();
		strategyConfig.setNaming(NamingStrategy.underline_to_camel);
		strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
		if (StringUtils.isNotBlank(strategSuperControllerClass)) {
			strategyConfig.setSuperControllerClass(Class.forName(strategSuperControllerClass));
		}
		strategyConfig.setEntityLombokModel(true);
		strategyConfig.setRestControllerStyle(true);
		//公共父类
//		strategyConfig.setSuperControllerClass("com.baomidou.ant.common.BaseController");
		//写于父类中的公共字段
//		strategyConfig.setSuperEntityColumns("id");
		strategyConfig.setInclude(tables);//表名
		strategyConfig.setControllerMappingHyphenStyle(true);
		if (StringUtils.isNotBlank(strategTablePrefix)) {
			strategyConfig.setTablePrefix(strategTablePrefix);
		}
		generator.setStrategy(strategyConfig);
		
		/**=== 模板引擎 ===**/
		//默认模板引擎：Velocity
		//若选择了非默认引擎，需要在 AutoGenerator 中 设置模板引擎
		//Freemarker模板引擎
		generator.setTemplateEngine(new FreemarkerTemplateEngine());
		//Beetl模板引擎
//		generator.setTemplateEngine(new BeetlTemplateEngine());
		//自定义模板引擎,继承com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine
//		generator.setTemplateEngine(new CustomTemplateEngine());
		
		generator.execute();
	}

	private String getProperty(String property) {
		return placeholdersResolver.resolvePlaceholders(property).toString();
	}

	private Map<String, Object> getSystemEnvironment() {
		Map<String, Object> systemEnvironment = new HashMap<>();
		for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
			systemEnvironment.put(entry.getKey(), entry.getValue());
		}
		for (Map.Entry<Object, Object> entry : System.getProperties().entrySet()) {
			systemEnvironment.put(entry.getKey().toString(), entry.getValue());
		}
		return systemEnvironment;
	}
	
}

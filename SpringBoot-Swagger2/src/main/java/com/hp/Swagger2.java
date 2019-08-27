package com.hp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * 通过@Configuration注解，让Spring来加载该类配置
 * 在通过@EnableSwagger2注解来启用Swagger2，表示这是一个Spring Swagger的配置文件。
 * @author hp
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
	
	@Bean
	public Docket createRestApi() {
		//DocumentationType.SWAGGER_2作为Docket构造方法的参数，指定了所用的swagger版本2.0
		//Springfox的主要api配置机制初始化为swagger规范2.0
		return new Docket(DocumentationType.SWAGGER_2) 
				// apiInfo则是调用接下来的apiInfo函数，来创建Docket的信息
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.hp.controller"))  //api接口包扫描路径
				//可以根据url路径设置哪些请求加入文档，忽略哪些请求。这种方式可以通过筛选 API 的 url 来进行过滤
				/*
				 * .paths(Predicates.or(PathSelectors.ant("/user/add"),
                 *                      PathSelectors.ant("/user/find/*")))
                 * 接口文档将只会展示 /user/add 和 /user/find/{id} 两个接口。
				 */
				.paths(PathSelectors.any()) 
				.build();
	}

	private ApiInfo apiInfo() {
		// apiInfo函数采用ApiInfoBuilder来创建ApiInfo类
		return new ApiInfoBuilder()
				.title("Spring Boot中使用Swagger2构建RESTful APIs")
				.description("Spring Boot入门学习相关代码地址：https://github.com/hp1637038648/springboot-study")
				.termsOfServiceUrl("https://github.com/hp1637038648/springboot-study")  //设置文档的License信息
				.contact("胡蓬")
				.version("1.0")
				.build();
	}
}

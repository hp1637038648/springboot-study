package com.hp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
/*
 * 在Spring Boot中通过@EnableCaching注解自动化配置合适的缓存管理器（CacheManager）
 */
@EnableCaching  //通过该注解开启缓存支持
public class SpringBootJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaApplication.class, args);
	}

}

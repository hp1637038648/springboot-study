package com.hp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync  // 启用该注解使@Async注解生效
public class SpringBootAsyncCallApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAsyncCallApplication.class, args);
	}

}

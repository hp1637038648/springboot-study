package com.hp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * @SpringBootApplication为SpringBoot启动类的注解
 * @SpringBootApplication 注解实际上是 SpringBoot提供的一个复合注解，可理解为三体注解
 * 包含：@SpringBootConfiguration，@EnableAutoConfiguration，@ComponentScan
 */
@SpringBootApplication
public class SpringBootIntroductionApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootIntroductionApplication.class, args);
	}
}

/*
 * @SpringBootConfiguration，@EnableAutoConfiguration，@ComponentScan
 * 这三个注解代替@SpringBootApplication也可以当SpringBoot启动类
 */
package com.hp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AopStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopStudyApplication.class, args);
	}

}

package com.hp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.hp.service","com.hp.config"})
public class SpringBootJtaTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJtaTransactionApplication.class, args);
	}

}

package com.hp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.hp.mapper")
public class SpringBootMybatisXmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisXmlApplication.class, args);
	}

}

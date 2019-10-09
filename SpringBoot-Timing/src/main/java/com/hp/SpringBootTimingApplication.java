package com.hp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // 通过该配置启用定时任务
public class SpringBootTimingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTimingApplication.class, args);
	}

}

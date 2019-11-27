package com.hp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import com.hp.service.Arithmetic;

@ComponentScan(basePackages = "com.hp.*")
public class AdvanceMain {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AdvanceMain.class);
		Arithmetic arithmetic = (Arithmetic) context.getBean("calculator");
		arithmetic.add(10, 30);
	}
}

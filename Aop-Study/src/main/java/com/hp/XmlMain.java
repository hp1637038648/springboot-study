package com.hp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.hp.service.Arithmetic;

public class XmlMain {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringAop.xml");
		Arithmetic arithmetic = (Arithmetic) context.getBean("calculator");
		arithmetic.add(10, 5);
	}
}

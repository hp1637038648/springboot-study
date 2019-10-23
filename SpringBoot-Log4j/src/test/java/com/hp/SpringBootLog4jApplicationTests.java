package com.hp;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootLog4jApplicationTests {

	private Logger logger = Logger.getLogger(getClass());
	
	@Test
	public void contextLoads() {
		logger.info("输出info");
		logger.info("输出debug");
		logger.error("输出error");
		logger.debug("倒是不错就是vhdkjvhd");
		logger.error("错误信息");
	}

}

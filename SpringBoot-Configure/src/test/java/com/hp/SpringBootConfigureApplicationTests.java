package com.hp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hp.service.BlogProperties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootConfigureApplicationTests {

	@Autowired
	private BlogProperties blogProperties;
	
	private static final Log log = LogFactory.getLog(SpringBootConfigureApplicationTests.class);
	
	@Test
	public void contextLoads() throws Exception{
		Assert.assertEquals(blogProperties.getName(), "HuPeng");
		Assert.assertEquals(blogProperties.getTitle(), "SpringBoot-Study");
		Assert.assertEquals("HuPeng is writering <SpringBoot-Study>", blogProperties.getDesc());
		
		log.info("随机数测试输出：");
		log.info("随机字符串 : " + blogProperties.getValue());
		log.info("随机int : " + blogProperties.getNumber());
		log.info("随机long : " + blogProperties.getBignumber());
		log.info("随机10以下 : " + blogProperties.getTest1());
		log.info("随机10-20 : " + blogProperties.getTest2());
	}

}

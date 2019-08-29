package com.hp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hp.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootUseJdbcTemplateApplicationTests {
    
	@Autowired
	private UserService userService;
	
	@Before
	public void setUp() {
		// 准备，清空user表
		userService.deleteAllUsers();
	}
	
	@Test
	public void test() throws Exception {
		// 插入5个用户
		userService.create("a", 1);
		userService.create("b", 2);
		userService.create("c", 3);
		userService.create("d", 4);
		userService.create("e", 5);
		
		// 查询数据库，应该有5个用户
		Assert.assertEquals(new Integer(5), userService.getAllUsers());
		
		// 删除2个用户
		userService.deleteByName("a");
		userService.deleteByName("b");
		
		// 查询数据库，应该有3个用户
		Assert.assertEquals(new Integer(3), userService.getAllUsers());
	}
}

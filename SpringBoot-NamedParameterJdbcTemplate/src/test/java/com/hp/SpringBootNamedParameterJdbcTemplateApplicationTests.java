package com.hp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hp.dao.UserDao;
import com.hp.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootNamedParameterJdbcTemplateApplicationTests {
	
	@Autowired
	private UserDao userDao;

	@Test
	public void insert() {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setName("山姆");
		user.setAge(26);
		int count = userDao.insert(user);
		System.out.println(count);
	}
	
	@Test
	public void batchInsert() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        user1.setName("琼恩");
        user1.setAge(27);

        User user2 = new User();
        user2.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        user2.setName("罗柏");
        user2.setAge(28);

        users.add(user1);
        users.add(user2);

        userDao.batchInsert(users);
	}
	
	@Test
	public void getById() {
        User byId = userDao.getById("c91655bd4caf4d83997f6285a71f2c5c");
        System.out.println(byId);
	}
	
    @Test
    public void queryList() {
        User query = new User();
        query.setName("恩");
        List<User> users = userDao.queryList(query);
        for (User us : users) {
            System.out.println(us.toString());
        }
    }
    
    @Test
    public void count() {
        User query = new User();
        query.setName("恩");
        System.out.println(userDao.count(query));
    }
    
    @Test
    public void update() {
        User user = new User();
        user.setId("dcf2cdfd-1aaa-4f66-a67d-20a6cf1fa7c5");
        user.setName("哈哈哈");
        user.setAge(100);
        System.out.println(userDao.update(user));
    }
    
    @Test
    public void delete() {
        System.out.println(userDao.deleteById("dcf2cdfd-1aaa-4f66-a67d-20a6cf1fa7c5"));
    }
    
    @Test
    public void batchUpdate() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId("5ccae5d5482244278b1cb8d9d85a05ca");
        user.setName("fuck");
        user.setAge(200);
        User user2 = new User();
        user2.setId("c91655bd4caf4d83997f6285a71f2c5c");
        user2.setName("heihei");
        user2.setAge(300);
        users.add(user);
        users.add(user2);
        userDao.batchUpdate(users);
    }

}

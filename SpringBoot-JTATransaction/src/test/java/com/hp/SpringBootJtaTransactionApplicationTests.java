package com.hp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.hp.dao.customer.CustomerRepository;
import com.hp.dao.order.OrderRepository;
import com.hp.entity.customer.Customer;
import com.hp.entity.order.Orders;
import com.hp.service.StoreService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(transactionManager = "transactionManager")
public class SpringBootJtaTransactionApplicationTests {
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Test
	@Rollback(false) //在执行数据操作时开启了事务，数据被放在一级缓存中，而操作之后没有提交事务。在测试的方法添加@Rollback(false)即可修改数据库
	public void testStore() {
		Customer c = new Customer();
		c.setName("test1");
		c.setAge(23);

		Orders o = new Orders();
		o.setCode(1);
		o.setQuantity(7);

		storeService.store(c, o);

		Assert.assertNotNull(c.getId());
		Assert.assertNotNull(o.getId());

		Assert.assertEquals(1, customerRepository.findAll().size());
		Assert.assertEquals(1, orderRepository.findAll().size());
	}
	
	@Test
	@Rollback(true)
	public void testStoreWithStoreException() throws NullPointerException {
		Customer c = new Customer();
		c.setName("test");
		c.setAge(30);

		Orders o = new Orders();
		o.setCode(12);
		o.setQuantity(7);

		Assert.assertEquals(1, customerRepository.findAll().size());
		Assert.assertEquals(1, orderRepository.findAll().size());

		storeService.storeWithStoreException(c, o);
		System.out.println("66666666666666666666");
	}

}

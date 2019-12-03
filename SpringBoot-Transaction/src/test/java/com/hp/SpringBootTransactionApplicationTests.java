package com.hp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.hp.entity.customer.Customer;
import com.hp.entity.order.Order;
import com.hp.repository.customer.CustomerRepository;
import com.hp.repository.order.OrderRepository;
import com.hp.service.StoreService;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional(transactionManager = "transactionManager")
public class SpringBootTransactionApplicationTests {
	
	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private StoreService storeService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Test
	@Transactional
	public void testStore() throws Exception {
		Customer c = new Customer();
		c.setName("test");
		c.setAge(30);

		Order o = new Order();
		o.setCode(1);
		o.setQuantity(7);

		storeService.store(c, o);

		Assert.assertNotNull(c.getId());
		Assert.assertNotNull(o.getId());

		Assert.assertEquals(1, customerRepository.findAll().size());
		Assert.assertEquals(1, orderRepository.findAll().size());
	}

}

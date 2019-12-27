package com.hp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hp.entity.customer.Customer;
import com.hp.entity.order.Orders;
import com.hp.service.StoreService;

@RestController
@RequestMapping("/api")
public class TestController {

	@Autowired
	private StoreService storeService;
	
	@GetMapping("/add")
	public String test() {
		Customer c = new Customer();
		c.setName(null);  // 通过name不能为空进行回滚测试
		c.setAge(23);

		Orders o = new Orders();
		o.setCode(2);
		o.setQuantity(8);

		storeService.store(c, o);
		return "success";
	}
}

package com.hp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hp.dao.customer.CustomerRepository;
import com.hp.dao.order.OrderRepository;
import com.hp.entity.customer.Customer;
import com.hp.entity.order.Orders;
import com.hp.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired 
	private OrderRepository orderRepository;

	@Transactional
	public void store(Customer customer, Orders order) {
		orderRepository.save(order);
//		orderRepository.save(new Orders(99,100));
		customerRepository.save(customer);
	}

	@Transactional(rollbackFor = NullPointerException.class)
	public void storeWithStoreException(Customer customer, Orders order) {
		customerRepository.save(customer);
		orderRepository.save(order);
		throw new NullPointerException();
	}

	@Transactional(rollbackFor = ArithmeticException.class)
	public void storeWithNoRollbackException(Customer customer, Orders order) {
		orderRepository.save(order);
		customerRepository.save(customer);
		throw new ArithmeticException();
	}

}

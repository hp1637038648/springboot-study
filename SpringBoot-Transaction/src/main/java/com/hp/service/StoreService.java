package com.hp.service;

import com.hp.exception.NoRollbackException;
import com.hp.exception.StoreException;
import com.hp.entity.customer.Customer;
import com.hp.entity.order.Order;

public interface StoreService {
	
	void store(Customer customer, Order order) throws Exception;
	
	void storeWithStoreException(Customer customer, Order order) throws StoreException;
	
	void storeWithNoRollbackException(Customer customer, Order order) throws NoRollbackException;

}


package com.hp.service;

import com.hp.entity.customer.Customer;
import com.hp.entity.order.Orders;

public interface StoreService {
	
	void store(Customer customer, Orders order);
	
	void storeWithStoreException(Customer customer, Orders order);
	
	void storeWithNoRollbackException(Customer customer, Orders order);

}

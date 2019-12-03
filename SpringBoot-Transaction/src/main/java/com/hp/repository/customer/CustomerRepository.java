package com.hp.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hp.entity.customer.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}

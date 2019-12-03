package com.hp.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hp.entity.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}

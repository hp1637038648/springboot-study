package com.hp.dao.order;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hp.entity.order.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long>{

}

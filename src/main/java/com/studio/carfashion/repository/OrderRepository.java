package com.studio.carfashion.repository;

import com.studio.carfashion.model.Employee;
import com.studio.carfashion.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {

}

package com.studio.carfashion.service;

import com.studio.carfashion.model.Company;
import com.studio.carfashion.model.Order;
import com.studio.carfashion.repository.CompanyRepository;
import com.studio.carfashion.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;

@Service
@AllArgsConstructor
public class OrderService {

    private final CompanyRepository companyRepository;
    private final OrderRepository orderRepository;

    public void registerOrder(Integer companyId) {
        Company company = companyRepository.findById(companyId).get();
        Order order = new Order(0, LocalDate.now(), "ongoing", 0.0, 0.0, company, Collections.emptyList());
        orderRepository.save(order);
    }

    public Order findById(Integer orderId) {
        return orderRepository.findById(orderId).get();
    }
}

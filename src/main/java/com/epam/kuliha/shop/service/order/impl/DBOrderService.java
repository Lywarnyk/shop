package com.epam.kuliha.shop.service.order.impl;

import com.epam.kuliha.shop.db.TransactionManager;
import com.epam.kuliha.shop.repository.OrderRepository;
import com.epam.kuliha.shop.service.order.Order;
import com.epam.kuliha.shop.service.order.OrderService;

public class DBOrderService implements OrderService {

    private TransactionManager transactionManager;
    private OrderRepository orderRepository;

    public DBOrderService(TransactionManager transactionManager, OrderRepository orderRepository) {
        this.transactionManager = transactionManager;
        this.orderRepository = orderRepository;
    }

    @Override
    public void add(Order order) {
        transactionManager.execute(()-> orderRepository.add(order));
    }
}

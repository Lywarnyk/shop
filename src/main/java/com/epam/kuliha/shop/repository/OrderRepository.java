package com.epam.kuliha.shop.repository;

import com.epam.kuliha.shop.service.order.Order;

import java.sql.SQLException;

public interface OrderRepository {

    boolean add(Order order) throws SQLException;
}

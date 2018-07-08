package com.epam.kuliha.shop.service.cart;

import com.epam.kuliha.shop.entity.Transport;
import com.epam.kuliha.shop.service.order.OrderItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {

    private Map<Transport, Integer> cart;

    public Cart() {
        cart = new HashMap<>();
    }

    public void add(Transport transport, int amount) {
        cart.merge(transport, amount, Integer::sum);
    }

    public void remove(int transportId) {
        cart.keySet().removeIf(t -> t.getId() == transportId);
    }

    public List<OrderItem> getOrderItems() {
        List<OrderItem> orderItems = new ArrayList<>();
        cart.forEach((key, value) -> orderItems.add(new OrderItem(key, value)));
        return orderItems;
    }

    public int getProductsAmount() {
        return cart.values().stream().mapToInt(Integer::intValue).sum();
    }

    public int getTotalPrice() {
        return cart.entrySet().stream()
                .mapToInt(k -> k.getKey().getPrice() * k.getValue())
                .sum();
    }

    public void set(Transport transport, int transportAmount) {
        cart.put(transport, transportAmount);
    }

    public void clear() {
        cart.clear();
    }
}

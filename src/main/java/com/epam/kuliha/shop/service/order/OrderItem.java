package com.epam.kuliha.shop.service.order;

import com.epam.kuliha.shop.entity.Transport;

public class OrderItem {
    private Transport transport;
    private int amount;
    private final int currentPrice;

    public OrderItem(Transport transport, int amount) {
        this.transport = transport;
        this.amount = amount;
        currentPrice = transport.getPrice();
    }

    public Transport getTransport() {
        return transport;
    }

    public int getAmount() {
        return amount;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }
}

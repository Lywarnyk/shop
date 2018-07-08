package com.epam.kuliha.shop.service.order;

import com.epam.kuliha.shop.entity.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.sql.Timestamp;
import java.util.List;

import static com.epam.kuliha.shop.service.order.Status.ACCEPTED;

public class Order {

    private static final String DEFAULT_STATUS_DESCRIPTION = "Order was created successfully";
    private int id;
    private Status status;
    private String statusDescription;
    private Timestamp creationDate;
    private User user;
    private List<OrderItem> items;
    private String deliveryInfo;
    private String payType;
    private String delivery;
    private String cardNumber;
    private int totalPrice;

    public Order() {
    }

    public Order(User user, List<OrderItem> items) {
        status = ACCEPTED;
        statusDescription = DEFAULT_STATUS_DESCRIPTION;
        this.user = user;
        this.items = items;
        creationDate = new Timestamp(System.currentTimeMillis());
        totalPrice = items.stream().mapToInt(OrderItem::getCurrentPrice).sum();
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
        totalPrice = items.stream().mapToInt(OrderItem::getCurrentPrice).sum();
    }

    public String getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(String deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return new EqualsBuilder()
                .append(id, order.id)
                .append(status, order.status)
                .append(statusDescription, order.statusDescription)
                .append(creationDate, order.creationDate)
                .append(user, order.user)
                .append(items, order.items)
                .append(deliveryInfo, order.deliveryInfo)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(status)
                .append(statusDescription)
                .append(creationDate)
                .append(user)
                .append(items)
                .append(deliveryInfo)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("id", id)
                .append("status", status)
                .append("statusDescription", statusDescription)
                .append("creationDate", creationDate)
                .append("user", user)
                .append("items", items)
                .append("deliveryInfo", deliveryInfo)
                .toString();
    }
}

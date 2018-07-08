package com.epam.kuliha.shop.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PaymentInfoBean {
    private String payType;
    private String cardNumber;
    private String delivery;
    private String description;

    public PaymentInfoBean() {
    }

    public PaymentInfoBean(String payType, String cardNumber, String delivery, String description) {
        this.payType = payType;
        this.cardNumber = cardNumber;
        this.delivery = delivery;
        this.description = description;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PaymentInfoBean that = (PaymentInfoBean) o;

        return new EqualsBuilder()
                .append(payType, that.payType)
                .append(cardNumber, that.cardNumber)
                .append(delivery, that.delivery)
                .append(description, that.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(payType)
                .append(cardNumber)
                .append(delivery)
                .append(description)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("payType", payType)
                .append("cardNumber", cardNumber)
                .append("delivery", delivery)
                .append("description", description)
                .toString();
    }
}

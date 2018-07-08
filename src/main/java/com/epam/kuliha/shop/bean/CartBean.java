package com.epam.kuliha.shop.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CartBean {

    private int totalPrice;
    private int transportsAmount;

    public CartBean(int totalPrice, int transportsAmount) {
        this.totalPrice = totalPrice;
        this.transportsAmount = transportsAmount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTransportsAmount() {
        return transportsAmount;
    }

    public void setTransportsAmount(int transportsAmount) {
        this.transportsAmount = transportsAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CartBean cartBean = (CartBean) o;

        return new EqualsBuilder()
                .append(totalPrice, cartBean.totalPrice)
                .append(transportsAmount, cartBean.transportsAmount)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(totalPrice)
                .append(transportsAmount)
                .toHashCode();
    }
}

package com.epam.kuliha.shop.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class JSONRequestBean {

    private String transportId;
    private String transportAmount;

    public JSONRequestBean() {
    }

    public JSONRequestBean(String transportId, String transportAmount) {
        this.transportId = transportId;
        this.transportAmount = transportAmount;
    }

    public String getTransportId() {
        return transportId;
    }

    public void setTransportId(String transportId) {
        this.transportId = transportId;
    }

    public String getTransportAmount() {
        return transportAmount;
    }

    public void setTransportAmount(String transportAmount) {
        this.transportAmount = transportAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        JSONRequestBean that = (JSONRequestBean) o;

        return new EqualsBuilder()
                .append(transportId, that.transportId)
                .append(transportAmount, that.transportAmount)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(transportId)
                .append(transportAmount)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("transportId", transportId)
                .append("transportAmount", transportAmount)
                .toString();
    }
}

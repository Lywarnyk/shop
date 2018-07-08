package com.epam.kuliha.shop.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Transport {

    private int id;
    private String model;
    private int price;
    private Category category;
    private Manufacturer manufacturer;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
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

        Transport transport = (Transport) o;

        return new EqualsBuilder()
                .append(id, transport.id)
                .append(price, transport.price)
                .append(category, transport.category)
                .append(manufacturer, transport.manufacturer)
                .append(model, transport.model)
                .append(description, transport.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(model)
                .append(price)
                .append(category)
                .append(manufacturer)
                .append(description)
                .toHashCode();
    }
}

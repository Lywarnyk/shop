package com.epam.kuliha.shop.entity;

public class Filter {

    private int minPrice;
    private int maxPrice;
    private int[] categories;
    private String name;
    private int manufacturer;
    private int itemsPerPage;
    private String sort;
    private String order;
    private int page;

    public Filter() {
    }

    public Filter(int minPrice, int maxPrice, int[] categories, String name, int manufacturer, int itemsPerPage, String sort, String order, int page) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.categories = categories;
        this.name = name;
        this.manufacturer = manufacturer;
        this.itemsPerPage = itemsPerPage;
        this.sort = sort;
        this.order = order;
        this.page = page;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public int[] getCategories() {
        return categories;
    }

    public String getName() {
        return name;
    }

    public int getManufacturer() {
        return manufacturer;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public String getSort() {
        return sort;
    }

    public String getOrder() {
        return order;
    }

    public int getPage() {
        return page;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setCategories(int[] categories) {
        this.categories = categories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(int manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setPage(int page) {
        this.page = page;
    }
}

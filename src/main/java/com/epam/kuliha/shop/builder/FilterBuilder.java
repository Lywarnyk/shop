package com.epam.kuliha.shop.builder;

import com.epam.kuliha.shop.entity.Filter;
import com.epam.kuliha.shop.exception.NegativeIntegerException;
import com.epam.kuliha.shop.service.validator.FilterValidator;
import org.apache.log4j.Logger;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class FilterBuilder {

    private static final Logger LOG = Logger.getLogger(FilterBuilder.class);
    private static final String MAX_PRICE_IS_INVALID = "Max price is invalid";
    private static final String MIN_PRICE_IS_INVALID = "Min price is invalid";
    private static final String CATEGORIES_ARE_INVALID = "Categories are invalid";
    private static final String MANUFACTURER_IS_INVALID = "Manufacturer is invalid";
    private static final String PAGE_IS_INVALID = "Page is invalid";
    private static final String DEFAULT_SORT = "model";
    private static final String DEFAULT_ORDER = "asc";
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_ITEMS_PER_PAGE = 10;
    private static final int DEFAULT_MANUFACTURER = 0;
    private static final int DEFAULT_PRICE = 0;
    private FilterValidator filterValidator;

    private Filter filter;

    public FilterBuilder(FilterValidator filterValidator) {
        this.filterValidator = filterValidator;
        setUpDefaultParams();
    }


    public FilterBuilder setPrice(String from, String to) {
        setMinPrice(from);
        setMaxPrice(to);
        return this;
    }

    public FilterBuilder setCategories(String[] categories) {
        if (categories == null)
            return this;
        int[] temp;
        try {
            temp = parseStringArrayToInteger(categories);
            filter.setCategories(temp);
        } catch (NumberFormatException e) {
            LOG.error(CATEGORIES_ARE_INVALID);
        }
        return this;
    }

    public FilterBuilder setName(String name) {
        if (filterValidator.isValidName(name))
            filter.setName(name);
        return this;
    }

    public FilterBuilder setManufacturer(String manufacturer) {
        int temp;
        try {
            temp = Integer.parseInt(manufacturer);
            if (filterValidator.isValidInt(temp)) {
                filter.setManufacturer(temp);
            }
        } catch (NumberFormatException e) {
            LOG.error(MANUFACTURER_IS_INVALID);
        }
        return this;
    }

    public FilterBuilder setItemsPerPage(String itemsPerPage) {
        int temp;
        try {
            temp = Integer.parseInt(itemsPerPage);
            if (filterValidator.isValidItemsPerPage(temp)) {
                filter.setItemsPerPage(temp);
            }
        } catch (NumberFormatException e) {
            LOG.error(MAX_PRICE_IS_INVALID);
        }
        return this;
    }

    public FilterBuilder setSort(String sort) {
        if (filterValidator.isValidSort(sort))
            filter.setSort(sort);
        return this;
    }

    public FilterBuilder setOrder(String order) {
        if (filterValidator.isValidOrder(order))
            filter.setOrder(order);
        return this;
    }

    public FilterBuilder setPage(String page) {
        int temp;
        try {
            temp = Integer.parseInt(page);
            if (filterValidator.isValidInt(temp)) {
                filter.setPage(temp);
            }
        } catch (NumberFormatException e) {
            LOG.error(PAGE_IS_INVALID);
        }
        return this;
    }

    public Filter build() {
        return filter;
    }

    private int[] parseStringArrayToInteger(String[] categories) {
        int[] temp = new int[categories.length];
        for (int i = 0; i < categories.length; i++) {
            int category = Integer.parseInt(categories[i]);
            if (filterValidator.isValidInt(category)) {
                temp[i] = category;
            } else
                throw new NegativeIntegerException();
        }
        return temp;
    }

    private void setUpDefaultParams() {
        filter = new Filter();
        filter.setMinPrice(DEFAULT_PRICE);
        filter.setMaxPrice(DEFAULT_PRICE);
        filter.setCategories(new int[0]);
        filter.setName(EMPTY);
        filter.setManufacturer(DEFAULT_MANUFACTURER);
        filter.setItemsPerPage(DEFAULT_ITEMS_PER_PAGE);
        filter.setSort(DEFAULT_SORT);
        filter.setOrder(DEFAULT_ORDER);
        filter.setPage(DEFAULT_PAGE);
    }

    private void setMinPrice(String from) {
        int fromInt;
        try {
            fromInt = Integer.parseInt(from);
            if (filterValidator.isValidInt(fromInt))
                filter.setMinPrice(fromInt);
        } catch (NumberFormatException e) {
            LOG.error(MIN_PRICE_IS_INVALID);
        }
    }

    private void setMaxPrice(String maxPrice) {
        int temp;
        try {
            temp = Integer.parseInt(maxPrice);
            if (filterValidator.isValidInt(temp) && temp > filter.getMinPrice()) {
                filter.setMaxPrice(temp);
            }
        } catch (NumberFormatException e) {
            LOG.error(MAX_PRICE_IS_INVALID);
        }
    }
}

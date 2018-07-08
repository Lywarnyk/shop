package com.epam.kuliha.shop.service.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterValidator {

    private List<Integer> itemsPerPageOptions;
    private List<String> sortOptions;
    private List<String> orderOptions;

    public FilterValidator() {
        itemsPerPageOptions = new ArrayList<>(Arrays.asList(3, 5, 10, 15, 20, 30, 40, 50));
        sortOptions = new ArrayList<>(Arrays.asList("name", "price"));
        orderOptions = new ArrayList<>(Arrays.asList("asc", "desc"));
    }

    public boolean isValidInt(int value) {
        return value > 0;
    }

    public boolean isValidName(String name) {
        if(name == null)
            return false;
        Pattern pattern = Pattern.compile("[\\dA-Za-z.,|\\-\\s]{1,55}");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public boolean isValidItemsPerPage(int items) {
        return itemsPerPageOptions.contains(items);
    }

    public boolean isValidSort(String sort) {
        return sortOptions.contains(sort);
    }

    public boolean isValidOrder(String order) {
        return orderOptions.contains(order);
    }
}

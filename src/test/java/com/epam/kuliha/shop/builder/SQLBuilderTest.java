package com.epam.kuliha.shop.builder;

import com.epam.kuliha.shop.entity.Filter;
import com.epam.kuliha.shop.service.validator.FilterValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SQLBuilderTest {

    String minPrice;
    String maxPrice;
    String[] categories;
    String name;
    String manufacturer;
    String itemsPerPage;
    String sort;
    String order;
    String page;

    String expectedQuery;

    FilterBuilder filterBuilder;
    Filter filter;
    SQLBuilder sqlBuilder;

    public SQLBuilderTest(String  minPrice, String  maxPrice, String [] categories, String name, String  manufacturer,
                          String  itemsPerPage, String sort, String order, String  page, String expectedQuery) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.categories = categories;
        this.name = name;
        this.manufacturer = manufacturer;
        this.itemsPerPage = itemsPerPage;
        this.sort = sort;
        this.order = order;
        this.page = page;
        this.expectedQuery = expectedQuery;
    }

    @Parameters
    public static Collection<Object[]> setUpParams(){
        Object[][] params = new Object[][]{
                {null, null, null, null, null, null, null, null, null, "SELECT transports.*, categories.*, manufacturers.* FROM transports INNER JOIN categories ON transports.categories_id = categories.id INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id WHERE price>0 ORDER BY model asc LIMIT 10 offset 0;"},
                {"1000", null, null, null, null, null, null, null, null, "SELECT transports.*, categories.*, manufacturers.* FROM transports INNER JOIN categories ON transports.categories_id = categories.id INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id WHERE price>1000 ORDER BY model asc LIMIT 10 offset 0;"},
                {"1000", "35001", null, null, null, null, null, null, null, "SELECT transports.*, categories.*, manufacturers.* FROM transports INNER JOIN categories ON transports.categories_id = categories.id INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id WHERE price>1000 AND price<35001 ORDER BY model asc LIMIT 10 offset 0;"},
                {"1000", "35001", new String[]{"1", "2", "3"}, null, null, null, null, null, null, "SELECT transports.*, categories.*, manufacturers.* FROM transports INNER JOIN categories ON transports.categories_id = categories.id INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id WHERE price>1000 AND price<35001 AND categories_id IN (1,2,3) ORDER BY model asc LIMIT 10 offset 0;"},
                {"1000", "35001", new String[]{"1", "2", "3"}, "Tesla", null, null, null, null, null, "SELECT transports.*, categories.*, manufacturers.* FROM transports INNER JOIN categories ON transports.categories_id = categories.id INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id WHERE price>1000 AND price<35001 AND categories_id IN (1,2,3) AND model like ? ORDER BY model asc LIMIT 10 offset 0;"},
                {"1000", "35001", new String[]{"1", "2", "3"}, null, "2", null, null, null, null, "SELECT transports.*, categories.*, manufacturers.* FROM transports INNER JOIN categories ON transports.categories_id = categories.id INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id WHERE price>1000 AND price<35001 AND categories_id IN (1,2,3) AND manufacturers_id=2 ORDER BY model asc LIMIT 10 offset 0;"},
                {"1000", "35001", new String[]{"1", "2", "3"}, null, "2", "3", null, null, null, "SELECT transports.*, categories.*, manufacturers.* FROM transports INNER JOIN categories ON transports.categories_id = categories.id INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id WHERE price>1000 AND price<35001 AND categories_id IN (1,2,3) AND manufacturers_id=2 ORDER BY model asc LIMIT 3 offset 0;"},
                {"1000", "35001", new String[]{"1", "2", "3"}, null, "2", "3", "price", null, null, "SELECT transports.*, categories.*, manufacturers.* FROM transports INNER JOIN categories ON transports.categories_id = categories.id INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id WHERE price>1000 AND price<35001 AND categories_id IN (1,2,3) AND manufacturers_id=2 ORDER BY price asc LIMIT 3 offset 0;"},
                {"1000", "35001", new String[]{"1", "2", "3"}, null, "2", "3", "price", "desc", null, "SELECT transports.*, categories.*, manufacturers.* FROM transports INNER JOIN categories ON transports.categories_id = categories.id INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id WHERE price>1000 AND price<35001 AND categories_id IN (1,2,3) AND manufacturers_id=2 ORDER BY price desc LIMIT 3 offset 0;"},
                {"1000", "35001", new String[]{"1", "2", "3"}, null, "2", "3", "price", "desc", "2", "SELECT transports.*, categories.*, manufacturers.* FROM transports INNER JOIN categories ON transports.categories_id = categories.id INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id WHERE price>1000 AND price<35001 AND categories_id IN (1,2,3) AND manufacturers_id=2 ORDER BY price desc LIMIT 3 offset 3;"},
                {"1000", "35001", new String[]{"1", "2", "3"}, null, "2", "3", "price", "asc", "2", "SELECT transports.*, categories.*, manufacturers.* FROM transports INNER JOIN categories ON transports.categories_id = categories.id INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id WHERE price>1000 AND price<35001 AND categories_id IN (1,2,3) AND manufacturers_id=2 ORDER BY price asc LIMIT 3 offset 3;"},
                {"asdf", "35001", new String[]{"1", "2", "3"}, null, "2", "3", "price", "asc", "2", "SELECT transports.*, categories.*, manufacturers.* FROM transports INNER JOIN categories ON transports.categories_id = categories.id INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id WHERE price>0 AND price<35001 AND categories_id IN (1,2,3) AND manufacturers_id=2 ORDER BY price asc LIMIT 3 offset 3;"},
                {"asdf", "asdf", new String[]{"1", "2", "3"}, null, "2", "10", "price", "asc", "1", "SELECT transports.*, categories.*, manufacturers.* FROM transports INNER JOIN categories ON transports.categories_id = categories.id INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id WHERE price>0 AND categories_id IN (1,2,3) AND manufacturers_id=2 ORDER BY price asc LIMIT 10 offset 0;"},
                {"asdf", "asdf", new String[]{"asdf", "asdf", "adsf3"}, null, "2", "10", "price", "asc", "1", "SELECT transports.*, categories.*, manufacturers.* FROM transports INNER JOIN categories ON transports.categories_id = categories.id INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id WHERE price>0 AND manufacturers_id=2 ORDER BY price asc LIMIT 10 offset 0;"},
                {"asdf", "asdf", new String[]{"asdf", "asdf", "adsf3"}, null, "asdf", "10", "price", "asc", "1", "SELECT transports.*, categories.*, manufacturers.* FROM transports INNER JOIN categories ON transports.categories_id = categories.id INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id WHERE price>0 ORDER BY price asc LIMIT 10 offset 0;"},
                {"asdf", "asdf", new String[]{"asdf", "asdf", "adsf3"}, null, "asdf", "asdf", "price", "asc", "1", "SELECT transports.*, categories.*, manufacturers.* FROM transports INNER JOIN categories ON transports.categories_id = categories.id INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id WHERE price>0 ORDER BY price asc LIMIT 10 offset 0;"},
                {"asdf", "asdf", new String[]{"asdf", "asdf", "adsf3"}, null, "asdf", "asdf", "asdf", "asc", "1", "SELECT transports.*, categories.*, manufacturers.* FROM transports INNER JOIN categories ON transports.categories_id = categories.id INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id WHERE price>0 ORDER BY model asc LIMIT 10 offset 0;"},
                {"asdf", "asdf", new String[]{"asdf", "asdf", "adsf3"}, null, "asdf", "asdf", "asdf", "asjhasdbf", "qwer", "SELECT transports.*, categories.*, manufacturers.* FROM transports INNER JOIN categories ON transports.categories_id = categories.id INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id WHERE price>0 ORDER BY model asc LIMIT 10 offset 0;"},
                {"asdf", "asdf", new String[]{"-1"}, null, "asdf", "asdf", "asdf", "asjhasdbf", "qwer", "SELECT transports.*, categories.*, manufacturers.* FROM transports INNER JOIN categories ON transports.categories_id = categories.id INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id WHERE price>0 ORDER BY model asc LIMIT 10 offset 0;"},
        };
        return Arrays.asList(params);
    }
    @Before
    public void setUp() throws Exception {
        sqlBuilder = new SQLBuilder();
        filterBuilder = new FilterBuilder(new FilterValidator());
        filter = filterBuilder
                .setPrice(minPrice, maxPrice)
                .setCategories(categories)
                .setName(name)
                .setManufacturer(manufacturer)
                .setItemsPerPage(itemsPerPage)
                .setSort(sort)
                .setOrder(order)
                .setPage(page)
                .build();
    }

    @Test
    public void buildQuery() {
        String actualQuery = sqlBuilder.buildQuery(filter);
        assertEquals(expectedQuery, actualQuery);
    }
}
package com.epam.kuliha.shop.builder;

import com.epam.kuliha.shop.entity.Filter;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.StringJoiner;

import static com.epam.kuliha.shop.constant.DBConstant.Column.TRANSPORT_PRICE;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class SQLBuilder {
    private static final Logger LOG = Logger.getLogger(SQLBuilder.class);

    private static final String STARTING_PART = "SELECT transports.*, categories.*, manufacturers.* ";
    private static final String STARTING_COUNT_PART = "SELECT COUNT(transports.id) ";
    private static final String JOIN_PART = "FROM transports " +
            "INNER JOIN categories ON transports.categories_id = categories.id " +
            "INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id " +
            "WHERE ";
    private static final char MORE_THAN = '>';
    private static final char SPACE = ' ';
    private static final String AND = "AND";
    private static final char LESS_THAN = '<';
    private static final String CATEGORIES_PREFIX = "AND categories_id IN (";
    private static final String SUFFIX = ") ";
    private static final String DELIMITER = ",";
    private static final char EQUALS = '=';
    private static final String MANUFACTURERS_ID = "manufacturers_id";
    private static final String NAME_CONDITION = "model like ?";
    private static final String END = ";";
    private static final String OFFSET = "offset";
    private static final String LIMIT = "LIMIT";
    private static final String ORDER_BY = "ORDER BY";

    public String buildCountQuery(Filter filter) {

        String query = new StringBuilder(STARTING_COUNT_PART)
                .append(JOIN_PART)
                .append(getMinPriceConditions(filter.getMinPrice()))
                .append(getMaxPriceConditions(filter.getMaxPrice()))
                .append(getCategoriesConditions(filter.getCategories()))
                .append(getManufacturerCondition(filter.getManufacturer()))
                .append(getModelCondition(filter.getName()))
                .append(getSortCondition(filter.getSort(), filter.getOrder()))
                .toString();
        LOG.debug(query);
        return query;
    }

    public String buildQuery(Filter filter) {

        String query = new StringBuilder(STARTING_PART)
                .append(JOIN_PART)
                .append(getMinPriceConditions(filter.getMinPrice()))
                .append(getMaxPriceConditions(filter.getMaxPrice()))
                .append(getCategoriesConditions(filter.getCategories()))
                .append(getManufacturerCondition(filter.getManufacturer()))
                .append(getModelCondition(filter.getName()))
                .append(getSortCondition(filter.getSort(), filter.getOrder()))
                .append(getLimitAndOffset(filter.getItemsPerPage(), filter.getPage()))
                .toString();
        LOG.debug(query);
        return query;
    }

    private String getMinPriceConditions(int minPrice) {
        return StringUtils.join(TRANSPORT_PRICE, MORE_THAN, minPrice, SPACE);
    }

    private String getMaxPriceConditions(int maxPrice) {
        if (maxPrice > 0)
            return StringUtils.join(AND, SPACE, TRANSPORT_PRICE, LESS_THAN, maxPrice, SPACE);
        return EMPTY;
    }

    private String getCategoriesConditions(int[] categories) {
        if(categories.length > 0) {
            StringJoiner sj = new StringJoiner(DELIMITER, CATEGORIES_PREFIX, SUFFIX);
            Arrays.stream(categories).forEach((s) -> sj.add(String.valueOf(s)));
            return sj.toString();
        }
        return EMPTY;
    }

    private String getManufacturerCondition(int manufacturer) {
        if (manufacturer != 0)
            return StringUtils.join(AND, SPACE, MANUFACTURERS_ID, EQUALS, manufacturer, SPACE);
        return EMPTY;
    }

    private String getModelCondition(String name) {
        if(!name.isEmpty())
            return StringUtils.join(AND,SPACE, NAME_CONDITION, SPACE);
        return EMPTY;
    }

    private String getSortCondition(String sort, String order) {
        return StringUtils.join(ORDER_BY, SPACE,  sort, SPACE, order, SPACE);
    }

    private String getLimitAndOffset(int itemsPerPage, int page) {
        return StringUtils.join(LIMIT, SPACE, itemsPerPage, SPACE, OFFSET, SPACE, itemsPerPage*(page-1), END);
    }
}

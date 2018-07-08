package com.epam.kuliha.shop.constant;

public class DBConstant {
    public static class Query {

        //users
        public static final String CREATE_USER = "INSERT INTO users VALUES (DEFAULT,?,?,?,?,?,?)";
        public static final String FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
        public static String UPDATE_USER = "UPDATE users SET first_name=?, last_name=?, email=? password=?, mailing=? WHERE id=?";
        public static String DELETE_USER = "DELETE FROM users WHERE id=?";

        //manufacturers
        public static final String GET_ALL_MANUFACTURERS = "SELECT * FROM manufacturers";

        //categories
        public static final String GET_ALL_CATEGORIES = "SELECT * FROM categories";

        //transport
        public static final String GET_TRANSPORT_BY_ID = "SELECT * FROM transports \n" +
                "INNER JOIN categories ON transports.categories_id = categories.id\n" +
                "INNER JOIN manufacturers ON transports.manufacturers_id = manufacturers.id\n" +
                " WHERE transports.id = ?";

        //orders
        public static final String CREATE_ORDER = "INSERT INTO orders VALUES (DEFAULT,?,?,?,?,?,?,?,?)";
        public static final String CREATE_ORDER_ITEM = "INSERT INTO order_items VALUES (?,?,?,?)";

    }

    public static class Column {
        public static final String ID = "id";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String MAILING = "mailing";

        //manufacturer
        public static final String MANUFACTURER_ID = "manufacturers.id";
        public static final String MANUFACTURER_NAME = "manufacturers.name";

        //category
        public static final String CATEGORY_ID = "categories.id";
        public static final String CATEGORY_NAME = "categories.name";
        public static final String TRANSPORT_ID = "transports.id";
        public static final String TRANSPORT_MODEL = "model";
        public static final String TRANSPORT_PRICE = "price";
        public static final String TRANSPORT_DESCRIPTION = "description";
        public static final String ROLE = "role";
    }
    public static class Error{

        public static final String CANNOT_OBTAIN_DATA_SOURCE = "cannot_obtain_data_source";
        public static final String CANNOT_OBTAIN_CONNECTION_FROM_DATA_SOURCE = "Cannot obtain connection from data source";
        public static final String CANNOT_CLOSE_CONNECTION = "Cannot close connection";
        public static final String CANNOT_ROLLBACK_TRANSACTION = "Cannot rollback transaction";
    }
}

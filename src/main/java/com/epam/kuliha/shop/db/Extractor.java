package com.epam.kuliha.shop.db;

import com.epam.kuliha.shop.entity.Category;
import com.epam.kuliha.shop.entity.Manufacturer;
import com.epam.kuliha.shop.entity.Transport;
import com.epam.kuliha.shop.entity.User;
import com.epam.kuliha.shop.security.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.kuliha.shop.constant.DBConstant.Column.FIRST_NAME;
import static com.epam.kuliha.shop.constant.DBConstant.Column.ID;
import static com.epam.kuliha.shop.constant.DBConstant.Column.LAST_NAME;
import static com.epam.kuliha.shop.constant.DBConstant.Column.EMAIL;
import static com.epam.kuliha.shop.constant.DBConstant.Column.PASSWORD;
import static com.epam.kuliha.shop.constant.DBConstant.Column.MAILING;
import static com.epam.kuliha.shop.constant.DBConstant.Column.MANUFACTURER_ID;
import static com.epam.kuliha.shop.constant.DBConstant.Column.MANUFACTURER_NAME;
import static com.epam.kuliha.shop.constant.DBConstant.Column.CATEGORY_ID;
import static com.epam.kuliha.shop.constant.DBConstant.Column.CATEGORY_NAME;
import static com.epam.kuliha.shop.constant.DBConstant.Column.ROLE;
import static com.epam.kuliha.shop.constant.DBConstant.Column.TRANSPORT_DESCRIPTION;
import static com.epam.kuliha.shop.constant.DBConstant.Column.TRANSPORT_ID;
import static com.epam.kuliha.shop.constant.DBConstant.Column.TRANSPORT_MODEL;
import static com.epam.kuliha.shop.constant.DBConstant.Column.TRANSPORT_PRICE;

public class Extractor {

    public static User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(ID));
        user.setFirstName(resultSet.getString(FIRST_NAME));
        user.setLastName(resultSet.getString(LAST_NAME));
        user.setEmail(resultSet.getString(EMAIL));
        user.setPassword(resultSet.getString(PASSWORD));
        user.setMailing(resultSet.getBoolean(MAILING));
        user.setRole(Role.valueOf(resultSet.getString(ROLE)));
        return user;
    }


    public static Manufacturer extractManufacturer(ResultSet resultSet) throws SQLException {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(resultSet.getInt(MANUFACTURER_ID));
        manufacturer.setName(resultSet.getString(MANUFACTURER_NAME));
        return manufacturer;
    }

    public static Category extractCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt(CATEGORY_ID));
        category.setName(resultSet.getString(CATEGORY_NAME));
        return category;
    }

    public static Transport extractTransport(ResultSet resultSet) throws SQLException {
        Transport transport = new Transport();
        transport.setId(resultSet.getInt(TRANSPORT_ID));
        transport.setModel(resultSet.getString(TRANSPORT_MODEL));
        transport.setPrice(resultSet.getInt(TRANSPORT_PRICE));
        transport.setCategory(extractCategory(resultSet));
        transport.setManufacturer(extractManufacturer(resultSet));
        transport.setDescription(resultSet.getString(TRANSPORT_DESCRIPTION));
        return transport;
    }
}

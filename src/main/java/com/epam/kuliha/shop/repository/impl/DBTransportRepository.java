package com.epam.kuliha.shop.repository.impl;

import com.epam.kuliha.shop.builder.SQLBuilder;
import com.epam.kuliha.shop.constant.DBConstant;
import com.epam.kuliha.shop.db.ConnectionHandler;
import com.epam.kuliha.shop.db.Extractor;
import com.epam.kuliha.shop.entity.Category;
import com.epam.kuliha.shop.entity.Filter;
import com.epam.kuliha.shop.entity.Manufacturer;
import com.epam.kuliha.shop.entity.Transport;
import com.epam.kuliha.shop.repository.TransportRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.epam.kuliha.shop.constant.DBConstant.Query.GET_ALL_CATEGORIES;

public class DBTransportRepository implements TransportRepository {

    private static final int START_PARAMETER_INDEX = 1;
    private ConnectionHandler connectionHandler;
    private SQLBuilder sqlBuilder;

    public DBTransportRepository(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
        this.sqlBuilder = new SQLBuilder();
    }


    @Override
    public List<Manufacturer> getAllManufacturers() throws SQLException {
        Connection connection = connectionHandler.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(DBConstant.Query.GET_ALL_MANUFACTURERS);
        List<Manufacturer> manufacturers = new ArrayList<>();
        while (resultSet.next()) {
            manufacturers.add(Extractor.extractManufacturer(resultSet));
        }
        return manufacturers;
    }

    @Override
    public List<Category> getAllCategories() throws SQLException {
        Connection connection = connectionHandler.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_CATEGORIES);
        List<Category> categories = new ArrayList<>();
        while (resultSet.next()) {
            categories.add(Extractor.extractCategory(resultSet));
        }
        return categories;
    }

    @Override
    public List<Transport> getTransportsByFilter(Filter filter) throws SQLException {
        String sqlQuery = sqlBuilder.buildQuery(filter);
        Connection connection = connectionHandler.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        if (!filter.getName().isEmpty()) {
            preparedStatement.setString(START_PARAMETER_INDEX, filter.getName());
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Transport> transports = new ArrayList<>();
        while (resultSet.next()) {
            transports.add(Extractor.extractTransport(resultSet));
        }
        return transports;
    }

    @Override
    public int getTransportCountByFilter(Filter filter) throws SQLException {
        int amount = 0;
        String sqlCountQuery = sqlBuilder.buildCountQuery(filter);
        Connection connection = connectionHandler.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlCountQuery);
        if (resultSet.next())
            amount = resultSet.getInt(START_PARAMETER_INDEX);
        return amount;
    }

    @Override
    public Transport getTransportCountById(int transportId) throws SQLException {
        Transport transport = null;
        Connection connection = connectionHandler.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(DBConstant.Query.GET_TRANSPORT_BY_ID);
        pstmt.setInt(START_PARAMETER_INDEX, transportId);
        ResultSet resultSet = pstmt.executeQuery();
        if(resultSet.next())
            transport = Extractor.extractTransport(resultSet);
        return transport;
    }
}

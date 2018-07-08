package com.epam.kuliha.shop.repository.impl;

import com.epam.kuliha.shop.constant.DBConstant;
import com.epam.kuliha.shop.db.ConnectionHandler;
import com.epam.kuliha.shop.repository.OrderRepository;
import com.epam.kuliha.shop.service.order.Order;
import com.epam.kuliha.shop.service.order.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import static com.epam.kuliha.shop.constant.DBConstant.Query.CREATE_ORDER;

public class DBOrderRepository implements OrderRepository {

    private static final int STARTING_PARAMETER_INDEX = 1;
    private static final int ID_COLUMN = 1;

    private ConnectionHandler connectionHandler;

    public DBOrderRepository(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    @Override
    public boolean add(Order order) throws SQLException {
        Connection connection = connectionHandler.getConnection();
        try (PreparedStatement orderStatement = connection.prepareStatement(CREATE_ORDER, Statement.RETURN_GENERATED_KEYS);
        PreparedStatement itemStatement = connection.prepareStatement(DBConstant.Query.CREATE_ORDER_ITEM)) {
            int k = STARTING_PARAMETER_INDEX;
            orderStatement.setInt(k++, order.getStatus().getId());
            orderStatement.setString(k++, order.getStatusDescription());
            orderStatement.setTimestamp(k++, order.getCreationDate());
            orderStatement.setInt(k++, order.getUser().getId());
            orderStatement.setString(k++, order.getDeliveryInfo());
            orderStatement.setString(k++, order.getCardNumber());
            orderStatement.setString(k++, order.getPayType());
            orderStatement.setString(k, order.getDelivery());

            if (orderStatement.executeUpdate() > 0) {
                ResultSet resultSet = orderStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    order.setId(resultSet.getInt(ID_COLUMN));
                    fillStatement(itemStatement, order);
                    int[] res = itemStatement.executeBatch();
                    return Arrays.stream(res).allMatch(i -> i == 1);
                }
            }
            return false;
        }
    }

    private void fillStatement(PreparedStatement itemStatement, Order order) throws SQLException {
        int orderId = order.getId();
        List<OrderItem> items = order.getItems();
        for (OrderItem item : items){
            int k = STARTING_PARAMETER_INDEX;
            itemStatement.setInt(k++, orderId);
            itemStatement.setInt(k++, item.getTransport().getId());
            itemStatement.setInt(k++, item.getCurrentPrice());
            itemStatement.setInt(k, item.getAmount());
            itemStatement.addBatch();
        }
    }
}

package com.epam.kuliha.shop.repository.impl;

import com.epam.kuliha.shop.db.ConnectionHandler;
import com.epam.kuliha.shop.db.Extractor;
import com.epam.kuliha.shop.entity.User;
import com.epam.kuliha.shop.repository.UserRepository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import static com.epam.kuliha.shop.constant.DBConstant.Query.CREATE_USER;
import static com.epam.kuliha.shop.constant.DBConstant.Query.FIND_USER_BY_EMAIL;
import static com.epam.kuliha.shop.constant.DBConstant.Query.UPDATE_USER;
import static com.epam.kuliha.shop.constant.DBConstant.Query.DELETE_USER;

public class DBUserRepository implements UserRepository {
    private static final int STARTING_PARAMETER_INDEX = 1;
    private static final int ID_COLUMN = STARTING_PARAMETER_INDEX;
    private ConnectionHandler connectionHandler;

    public DBUserRepository(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    @Override
    public boolean save(User user) throws SQLException {
        Connection connection = connectionHandler.getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
            int k = STARTING_PARAMETER_INDEX;
            pstmt.setString(k++, user.getFirstName());
            pstmt.setString(k++, user.getLastName());
            pstmt.setString(k++, user.getEmail());
            pstmt.setString(k++, user.getPassword());
            pstmt.setBoolean(k++, user.isMailing());
            pstmt.setString(k, user.getRole().name());

            if (pstmt.executeUpdate() > 0) {
                ResultSet resultSet = pstmt.getGeneratedKeys();
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(ID_COLUMN));
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public User getUser(String email) throws SQLException {
        Connection con = connectionHandler.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(FIND_USER_BY_EMAIL)) {
            pstmt.setString(STARTING_PARAMETER_INDEX, email);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return Extractor.extractUser(resultSet);
            }
            return null;
        }
    }

    @Override
    public boolean update(User user) throws SQLException {
        Connection con = connectionHandler.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_USER)) {
            int k = STARTING_PARAMETER_INDEX;
            pstmt.setString(k++, user.getFirstName());
            pstmt.setString(k++, user.getLastName());
            pstmt.setString(k++, user.getEmail());
            pstmt.setString(k++, user.getPassword());
            pstmt.setBoolean(k++, user.isMailing());
            pstmt.setInt(k, user.getId());
            return pstmt.execute();
        }
    }

    @Override
    public boolean delete(User user) throws SQLException {
        Connection con = connectionHandler.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(DELETE_USER)) {
            pstmt.setInt(STARTING_PARAMETER_INDEX, user.getId());
            return pstmt.executeUpdate() > 0;
        }
    }
}

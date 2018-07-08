package com.epam.kuliha.shop.db;

import com.epam.kuliha.shop.exception.ConnectionException;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static com.epam.kuliha.shop.constant.DBConstant.Error.CANNOT_CLOSE_CONNECTION;
import static com.epam.kuliha.shop.constant.DBConstant.Error.CANNOT_OBTAIN_CONNECTION_FROM_DATA_SOURCE;
import static com.epam.kuliha.shop.constant.DBConstant.Error.CANNOT_OBTAIN_DATA_SOURCE;
import static com.epam.kuliha.shop.constant.DBConstant.Error.CANNOT_ROLLBACK_TRANSACTION;

public class ConnectionPool {
    private static final Logger LOG = Logger.getLogger(ConnectionPool.class);

    private static final String DATA_SOURCE_NAME = "java:/comp/env/jdbc/transport-shop";
    private DataSource dataSource;

    public ConnectionPool() {
        try {
            Context initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup(DATA_SOURCE_NAME);
        } catch (NamingException ex) {
            LOG.error(CANNOT_OBTAIN_DATA_SOURCE, ex);
            throw new ConnectionException(CANNOT_OBTAIN_DATA_SOURCE, ex);
        }
    }

    public Connection getConnection() {
        Connection connection;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(CANNOT_OBTAIN_CONNECTION_FROM_DATA_SOURCE, e);
            throw new ConnectionException(CANNOT_OBTAIN_CONNECTION_FROM_DATA_SOURCE, e);
        }
        return connection;
    }

    public void close(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            LOG.error(CANNOT_CLOSE_CONNECTION, e);
            throw new ConnectionException(CANNOT_CLOSE_CONNECTION, e);
        }
    }

    public void rollback(Connection con) {
        try {
            con.rollback();
        } catch (SQLException e) {
            LOG.error(CANNOT_ROLLBACK_TRANSACTION, e);
            throw new ConnectionException(CANNOT_ROLLBACK_TRANSACTION, e);
        }
    }
}


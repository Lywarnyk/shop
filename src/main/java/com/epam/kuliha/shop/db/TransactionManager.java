package com.epam.kuliha.shop.db;

import com.epam.kuliha.shop.exception.DataBaseException;
import com.epam.kuliha.shop.exception.TransactionFailedException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private static final Logger LOG = Logger.getLogger(TransactionManager.class);

    private ConnectionHandler connectionHandler;
    private ConnectionPool connectionPool;

    public TransactionManager(ConnectionHandler connectionHandler, ConnectionPool connectionPool) {
        this.connectionHandler = connectionHandler;
        this.connectionPool = connectionPool;
    }

    public <T> T execute(DBRepositorySupplier method) {
        Connection connection = connectionPool.getConnection();
        T result;
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connectionHandler.setConnection(connection);
            result = (T) method.execute();
            connection.commit();
        } catch (DataBaseException | SQLException e) {
            LOG.error(e);
            connectionPool.rollback(connection);
            throw new TransactionFailedException(e);
        } finally {
            connectionHandler.remove();
            connectionPool.close(connection);
        }
        return result;
    }
}

package com.epam.kuliha.shop.db;

import java.sql.SQLException;

@FunctionalInterface
public interface DBRepositorySupplier<T> {

     T execute() throws SQLException;
}

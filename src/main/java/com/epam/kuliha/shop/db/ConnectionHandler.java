package com.epam.kuliha.shop.db;

import java.sql.Connection;

public class ConnectionHandler {

    private ThreadLocal<Connection> container;

    public ConnectionHandler() {
        container = new ThreadLocal<>();
    }

    public void setConnection(Connection connection){
        container.set(connection);
    }

    public Connection getConnection(){
        return container.get();
    }

    public void remove(){
        container.remove();
    }
}

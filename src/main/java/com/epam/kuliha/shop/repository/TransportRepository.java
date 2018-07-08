package com.epam.kuliha.shop.repository;

import com.epam.kuliha.shop.entity.Category;
import com.epam.kuliha.shop.entity.Filter;
import com.epam.kuliha.shop.entity.Manufacturer;
import com.epam.kuliha.shop.entity.Transport;

import java.sql.SQLException;
import java.util.List;

public interface TransportRepository {

    List<Manufacturer> getAllManufacturers() throws SQLException;

    List<Category> getAllCategories() throws SQLException;

    List<Transport> getTransportsByFilter(Filter filter) throws SQLException;

    int getTransportCountByFilter(Filter filter) throws SQLException;

    Transport getTransportCountById(int transportId) throws SQLException;
}

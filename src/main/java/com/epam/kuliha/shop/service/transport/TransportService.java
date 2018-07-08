package com.epam.kuliha.shop.service.transport;

import com.epam.kuliha.shop.entity.Category;
import com.epam.kuliha.shop.entity.Filter;
import com.epam.kuliha.shop.entity.Manufacturer;
import com.epam.kuliha.shop.entity.Transport;

import java.util.List;

public interface TransportService {

    List<Manufacturer> getAllManufacturers();

    List<Category> getAllCategories();

    List<Transport> getTransportByFilter(Filter filter);

    int getTransportCountByFilter(Filter filter);

    Transport getTransportById(int transportId);
}

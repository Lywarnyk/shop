package com.epam.kuliha.shop.service.transport.impl;

import com.epam.kuliha.shop.db.TransactionManager;
import com.epam.kuliha.shop.entity.Category;
import com.epam.kuliha.shop.entity.Filter;
import com.epam.kuliha.shop.entity.Manufacturer;
import com.epam.kuliha.shop.entity.Transport;
import com.epam.kuliha.shop.exception.TransportNotFoundException;
import com.epam.kuliha.shop.repository.TransportRepository;
import com.epam.kuliha.shop.service.transport.TransportService;

import java.util.List;

public class DBTransportService implements TransportService {

    private TransactionManager transactionManager;
    private TransportRepository transportRepository;

    public DBTransportService(TransactionManager transactionManager, TransportRepository transportRepository) {
        this.transactionManager = transactionManager;
        this.transportRepository = transportRepository;
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return transactionManager.execute(() -> transportRepository.getAllManufacturers());
    }

    @Override
    public List<Category> getAllCategories() {
        return transactionManager.execute(() -> transportRepository.getAllCategories());
    }

    @Override
    public List<Transport> getTransportByFilter(Filter filter) {
        return transactionManager.execute(() -> transportRepository.getTransportsByFilter(filter));
    }

    @Override
    public int getTransportCountByFilter(Filter filter) {
        return transactionManager.execute(() -> transportRepository.getTransportCountByFilter(filter));
    }

    @Override
    public Transport getTransportById(int transportId) {
        Transport transport = transactionManager.execute(() -> transportRepository.getTransportCountById(transportId));
        if (transport == null){
            throw new TransportNotFoundException();
        }
        return transport;
    }
}

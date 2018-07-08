package com.epam.kuliha.shop.service.user.impl;

import com.epam.kuliha.shop.db.TransactionManager;
import com.epam.kuliha.shop.entity.User;
import com.epam.kuliha.shop.exception.ExistentUserException;
import com.epam.kuliha.shop.exception.UserNotFoundException;
import com.epam.kuliha.shop.repository.UserRepository;
import com.epam.kuliha.shop.service.user.UserService;

public class DBUserService implements UserService {

    private TransactionManager transactionManager;
    private UserRepository userRepository;

    public DBUserService(TransactionManager transactionManager, UserRepository userRepository) {
        this.transactionManager = transactionManager;
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        boolean res = transactionManager.execute(() -> userRepository.save(user));
        if (!res)
            throw new ExistentUserException();
    }

    @Override
    public User getUser(String email) {
        User user = transactionManager.execute(() -> userRepository.getUser(email));
        if (user == null)
            throw new UserNotFoundException();
        return user;
    }

    @Override
    public void update(User user) {
        boolean res = transactionManager.execute(() -> userRepository.update(user));
        if (!res)
            throw new UserNotFoundException();
    }

    @Override
    public void delete(User user) {
        boolean res = transactionManager.execute(() -> userRepository.delete(user));
        if (!res)
            throw new UserNotFoundException();
    }
}

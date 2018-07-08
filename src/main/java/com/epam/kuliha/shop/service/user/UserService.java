package com.epam.kuliha.shop.service.user;

import com.epam.kuliha.shop.entity.User;
import com.epam.kuliha.shop.exception.ExistentUserException;
import com.epam.kuliha.shop.exception.UserNotFoundException;

/**
 * Service to work with user repository
 */
public interface UserService {
    /**
     * Saving user to repository
     *
     * @param user to be saved
     * @throws ExistentUserException if user with specified email is already exist.
     */
    void save(User user);

    /**
     * Returns User by email
     *
     * @param email specified user's email
     * @throws UserNotFoundException if user does not exist
     */
    User getUser(String email);

    /**
     * Updates user's information
     *
     * @param user to update
     * @throws UserNotFoundException if user does not exist
     */
    void update(User user);

    /**
     * Deleting user.
     *
     * @param user to delete
     * @throws UserNotFoundException if user does not exist
     */
    void delete(User user);
}

package com.epam.kuliha.shop.repository;

import com.epam.kuliha.shop.entity.User;
import com.epam.kuliha.shop.exception.ExistentUserException;
import com.epam.kuliha.shop.exception.UserNotFoundException;

import java.sql.SQLException;

/**
 * Repository for users
 */
public interface UserRepository {

    /**
     * Saving user to the repository
     *
     * @param user to be saved
     * @throws ExistentUserException when user with specified email is already exist.
     * @throws SQLException          if data access error occurs.
     */
    boolean save(User user) throws SQLException;

    /**
     * Returns User object
     *
     * @param email parameter for selecting user
     * @throws SQLException          if data access error occurs.
     * @throws UserNotFoundException if user with specified email does not exist
     */
    User getUser(String email) throws SQLException;

    /**
     * Updates user's information
     *
     * @param user to update
     * @return true if it was updated
     * @throws SQLException          if data access error occurs.
     * @throws UserNotFoundException if user does not exist
     */
    boolean update(User user) throws SQLException;

    /**
     * Deleting user.
     *
     * @param user to delete
     * @return true if it was deleted
     * @throws SQLException          if data access error occurs.
     * @throws UserNotFoundException if user does not exist
     */
    boolean delete(User user) throws SQLException;
}

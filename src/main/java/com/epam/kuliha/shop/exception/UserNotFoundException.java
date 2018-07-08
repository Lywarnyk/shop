package com.epam.kuliha.shop.exception;

public class UserNotFoundException extends WebApplicationException {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}

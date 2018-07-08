package com.epam.kuliha.shop.exception;

public class ExistentUserException extends WebApplicationException {

    public ExistentUserException() {
    }

    public ExistentUserException(String message) {
        super(message);
    }
}

package com.epam.kuliha.shop.exception;

public class CookieNotFoundException extends WebApplicationException {

    public CookieNotFoundException() {
    }

    public CookieNotFoundException(String message) {
        super(message);
    }
}

package com.epam.kuliha.shop.exception;

public class WebApplicationException extends RuntimeException {
    public WebApplicationException() {
    }

    public WebApplicationException(String message) {
        super(message);
    }

    public WebApplicationException(Throwable cause) {
        super(cause);
    }

    public WebApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.epam.kuliha.shop.exception;

public class TransportNotFoundException extends WebApplicationException {
    public TransportNotFoundException() {
    }

    public TransportNotFoundException(String message) {
        super(message);
    }

    public TransportNotFoundException(Throwable cause) {
        super(cause);
    }

    public TransportNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

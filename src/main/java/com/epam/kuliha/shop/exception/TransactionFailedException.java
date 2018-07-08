package com.epam.kuliha.shop.exception;

public class TransactionFailedException extends WebApplicationException {
    public TransactionFailedException() {
    }

    public TransactionFailedException(Throwable cause) {
        super(cause);
    }

    public TransactionFailedException(String message) {
        super(message);
    }

    public TransactionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}

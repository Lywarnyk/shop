package com.epam.kuliha.shop.exception;

public class CaptchaException extends WebApplicationException {

    public CaptchaException() {
    }

    public CaptchaException(String message) {
        super(message);
    }
}

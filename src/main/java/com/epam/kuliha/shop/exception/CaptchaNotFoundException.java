package com.epam.kuliha.shop.exception;

public class CaptchaNotFoundException extends CaptchaException {

    public CaptchaNotFoundException() {
    }

    public CaptchaNotFoundException(String message) {
        super(message);
    }
}

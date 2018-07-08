package com.epam.kuliha.shop.service.validator;

import com.epam.kuliha.shop.captcha.Captcha;
import com.epam.kuliha.shop.constant.Constant;

import java.util.Map;

public class CaptchaValidator {

    public void validate(Captcha captcha, Long captchaTimeOut, String captchaValue, Map<String, String> errors) {
        if (captcha == null || captcha.isExpired(captchaTimeOut)) {
            errors.put(Constant.RegisterFormFields.CAPTCHA, "Captcha is expired");
        } else if (!captcha.getValue().equals(captchaValue)) {
            errors.put(Constant.RegisterFormFields.CAPTCHA, "You supposed to be a robot!?");
        }
    }
}

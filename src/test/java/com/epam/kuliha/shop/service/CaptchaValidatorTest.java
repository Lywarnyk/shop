package com.epam.kuliha.shop.service;

import com.epam.kuliha.shop.captcha.Captcha;
import com.epam.kuliha.shop.constant.Constant;
import com.epam.kuliha.shop.service.validator.CaptchaValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CaptchaValidatorTest {

    Map<String, String> errors;
    @Mock
    Captcha captcha;
    long captchaTimeOut;
    String value;
    CaptchaValidator captchaValidator;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        errors = new HashMap<>();
        captchaTimeOut = 5L;
        value = "test";
        captchaValidator = new CaptchaValidator();
    }

    @Test
    public void validate_NullCaptcha_ShouldReturnSpecifiedErrorMessage() {
        Captcha nullCaptcha = null;

        captchaValidator.validate(nullCaptcha, captchaTimeOut, value, errors);

        assertEquals("Captcha is expired", errors.get(Constant.RegisterFormFields.CAPTCHA));
    }

    @Test
    public void validate_ExpiredCaptcha_ShouldReturnSpecifiedErrorMessage() {
        when(captcha.isExpired(captchaTimeOut)).thenReturn(true);
        when(captcha.getValue()).thenReturn(value);

        captchaValidator.validate(captcha, captchaTimeOut, value, errors);

        assertEquals("Captcha is expired", errors.get(Constant.RegisterFormFields.CAPTCHA));
    }

    @Test
    public void validate_InvalidCaptchaValue_ShouldReturnSpecifiedErrorMessage() {
        when(captcha.isExpired(captchaTimeOut)).thenReturn(false);
        when(captcha.getValue()).thenReturn("incorrect");

        captchaValidator.validate(captcha, captchaTimeOut, value, errors);

        assertEquals("You supposed to be a robot!?", errors.get(Constant.RegisterFormFields.CAPTCHA));
    }

    @Test
    public void validate_CorrectCaptcha_ShouldReturnEmptyMap() {
        when(captcha.isExpired(captchaTimeOut)).thenReturn(false);
        when(captcha.getValue()).thenReturn(value);

        captchaValidator.validate(captcha, captchaTimeOut, value, errors);

        assertTrue(errors.isEmpty());
    }
}
package com.epam.kuliha.shop.captcha.impl;

import com.epam.kuliha.shop.captcha.Captcha;
import com.epam.kuliha.shop.captcha.CaptchaCleaner;
import com.epam.kuliha.shop.captcha.CaptchaService;
import com.epam.kuliha.shop.exception.CaptchaNotFoundException;
import com.epam.kuliha.shop.exception.CookieNotFoundException;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CookieCaptchaService implements CaptchaService {

    private static final String CAPTCHA_ID = "captchaId";
    private static final Logger LOG = Logger.getLogger(CookieCaptchaService.class);

    private Map<Long, Captcha> captchaMap;
    private long captchaTimeOut;

    public CookieCaptchaService(long captchaTimeOut) {
        this.captchaMap = new ConcurrentHashMap<>();
        this.captchaTimeOut = captchaTimeOut;
    }

    @Override
    public void initialise() {
        new CaptchaCleaner(captchaMap, captchaTimeOut);
        LOG.info("CookieCaptchaService is initialised");
    }

    @Override
    public void delete(Captcha captcha) {
        if (captcha == null) {
            return;
        }
        captchaMap.remove(captcha.getCreationDate());
    }

    @Override
    public void save(Captcha captcha, HttpServletRequest request, HttpServletResponse response) {
        response.addCookie(new Cookie(CAPTCHA_ID, String.valueOf(captcha.getCreationDate())));
        captchaMap.put(captcha.getCreationDate(), captcha);
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (CAPTCHA_ID.equals(cookie.getName())) {
                    return captchaMap.get(Long.parseLong(cookie.getValue()));
                }
            }
            throw new CaptchaNotFoundException();
        }
        throw new CookieNotFoundException();
    }

    @Override
    public boolean isHiddenField() {
        return false;
    }
}

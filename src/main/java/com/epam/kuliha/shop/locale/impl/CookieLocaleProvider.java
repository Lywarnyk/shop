package com.epam.kuliha.shop.locale.impl;

import com.epam.kuliha.shop.locale.LocaleProvider;

import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Locale;

import static com.epam.kuliha.shop.constant.Constant.CURRENT_LOCALE;

public class CookieLocaleProvider implements LocaleProvider {

    private int cookieTimeout;

    public CookieLocaleProvider(int cookieTimeout) {
        this.cookieTimeout = cookieTimeout;
    }

    @Override
    public Locale getLocale(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals(CURRENT_LOCALE))
                .map(Cookie::getValue)
                .map(Locale::new)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void setLocale(HttpServletRequest request, Locale locale, ServletResponse resp) {
        Cookie  cookie= new Cookie(CURRENT_LOCALE, locale.toString());
        cookie.setMaxAge(cookieTimeout);
        ((HttpServletResponse)resp).addCookie(cookie);
    }
}

package com.epam.kuliha.shop.locale.impl;

import com.epam.kuliha.shop.locale.LocaleProvider;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

import static com.epam.kuliha.shop.constant.Constant.CURRENT_LOCALE;

public class SessionLocaleProvider implements LocaleProvider {

    @Override
    public Locale getLocale(HttpServletRequest request) {
        if (request.getSession().getAttribute(CURRENT_LOCALE) != null)
            return (Locale) request.getSession().getAttribute(CURRENT_LOCALE);
        return null;
    }

    @Override
    public void setLocale(HttpServletRequest request, Locale locale, ServletResponse resp) {
        HttpSession session = request.getSession();
        session.setAttribute(CURRENT_LOCALE, locale);
    }
}

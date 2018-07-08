package com.epam.kuliha.shop.locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;

public class RequestLocaleWrapper extends HttpServletRequestWrapper {

    private Locale locale;

    public RequestLocaleWrapper(HttpServletRequest request, Locale locale) {
        super(request);
        this.locale = locale;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return Collections.enumeration(Collections.singletonList(locale));
    }
}

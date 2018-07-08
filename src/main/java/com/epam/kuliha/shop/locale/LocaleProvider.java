package com.epam.kuliha.shop.locale;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public interface LocaleProvider {

    Locale getLocale(HttpServletRequest request);

    void setLocale(HttpServletRequest request, Locale locale, ServletResponse resp);

}

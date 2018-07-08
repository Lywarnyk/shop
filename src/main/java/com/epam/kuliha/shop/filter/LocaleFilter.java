package com.epam.kuliha.shop.filter;

import com.epam.kuliha.shop.locale.LocaleProvider;
import com.epam.kuliha.shop.locale.LocaleProvidersContainer;
import com.epam.kuliha.shop.locale.RequestLocaleWrapper;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class LocaleFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(LocaleFilter.class);

    private static final String LANG = "lang";
    private static final String DEFAULT_LOCALE = "default-locale";
    private static final String LOCALES = "locales";
    private static final String PROVIDER = "provider";
    private static final String COOKIE_TIMEOUT = "cookie-timeout";
    private LocaleProvider localeProvider;
    private List<Locale> locales;
    private Locale defaultLocale;

    @Override
    public void init(FilterConfig filterConfig) {
        locales = initLocales(filterConfig);
        defaultLocale = new Locale(filterConfig.getInitParameter(DEFAULT_LOCALE));
        int cookieTimeout = Integer.parseInt(filterConfig.getInitParameter(COOKIE_TIMEOUT));
        localeProvider = new LocaleProvidersContainer(cookieTimeout).getStorage(filterConfig.getInitParameter(PROVIDER));
        filterConfig.getServletContext().setAttribute(LOCALES, locales);
    }

    private List<Locale> initLocales(FilterConfig filterConfig) {
        List<Locale> locales = new ArrayList<>();
        String[] params = filterConfig.getInitParameter(LOCALES).split(",");
        Arrays.stream(params).forEach(p -> locales.add(LocaleUtils.toLocale(p.trim())));
        return locales;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        Locale locale;

        if (request.getParameter(LANG) != null) {
            locale = setLocaleIfParameterExists(request);
        } else if (localeProvider.getLocale(request) == null) {
            LOG.debug("storage loc == null");
            locale = setLocaleIfStorageIsEmpty(request);
        } else
            locale = localeProvider.getLocale(request);

        localeProvider.setLocale(request, locale, resp);
        request = new RequestLocaleWrapper(request, locale);
        chain.doFilter(request, resp);
    }

    private Locale setLocaleIfStorageIsEmpty(HttpServletRequest request) {
        Enumeration<Locale> browserLocales = request.getLocales();
        while (browserLocales.hasMoreElements()) {
            Locale locale = browserLocales.nextElement();
            if (locales.contains(locale)) {
                LOG.debug("Locale was set by locale from browser: " + locale);
                return locale;
            }
        }

        LOG.debug("Default locale was set");
        return defaultLocale;
    }

    private Locale setLocaleIfParameterExists(HttpServletRequest request) {
        String lang = request.getParameter(LANG);
        Optional<Locale> localeOptional = locales.stream()
                .filter(l -> l.getLanguage().equalsIgnoreCase(lang))
                .findFirst();

        if (localeOptional.isPresent()) {
            LOG.debug("Locale was set by parameter: " + lang);
            return localeOptional.get();
        }

        LOG.debug("Default locale was set");
        return defaultLocale;
    }

    @Override
    public void destroy() {
        //no action needed
    }
}

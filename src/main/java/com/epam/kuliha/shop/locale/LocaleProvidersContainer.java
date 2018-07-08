package com.epam.kuliha.shop.locale;

import com.epam.kuliha.shop.locale.impl.CookieLocaleProvider;
import com.epam.kuliha.shop.locale.impl.SessionLocaleProvider;

import java.util.HashMap;
import java.util.Map;

public class LocaleProvidersContainer {

    private static final String COOKIE = "cookie";
    private static final String SESSION = "session";
    private Map<String, LocaleProvider> localeStorageMap;

    public LocaleProvidersContainer(int cookieTimeout) {
        localeStorageMap = init(cookieTimeout);
    }

    public LocaleProvider getStorage(String param){
        return localeStorageMap.getOrDefault(param, localeStorageMap.get(SESSION));
    }

    private Map<String, LocaleProvider> init(int cookieTimeout) {
        Map<String, LocaleProvider> map = new HashMap<>();
        map.put(COOKIE, new CookieLocaleProvider(cookieTimeout));
        map.put(SESSION, new SessionLocaleProvider());
        return map;
    }
}

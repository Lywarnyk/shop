package com.epam.kuliha.shop.captcha;

import com.epam.kuliha.shop.captcha.impl.CookieCaptchaService;
import com.epam.kuliha.shop.captcha.impl.HiddenFieldsCaptchaService;
import com.epam.kuliha.shop.captcha.impl.SessionCaptchaService;
import com.epam.kuliha.shop.constant.Constant;

import java.util.HashMap;
import java.util.Map;

public class CaptchaServiceFactory {

    private Map<String, CaptchaService> storageMap;
    private long captchaTimeOut;

    public CaptchaServiceFactory(long captchaTimeOut) {
        this.captchaTimeOut = captchaTimeOut;
        initStorage();
    }

    public CaptchaService getCaptchaStorage(String storageParam) {
        return storageMap.getOrDefault(storageParam, storageMap.get(Constant.CaptchaStorage.SESSION));
    }

    private void initStorage() {
        storageMap = new HashMap<>();
        storageMap.put(Constant.CaptchaStorage.SESSION, new SessionCaptchaService());
        storageMap.put(Constant.CaptchaStorage.COOKIE, new CookieCaptchaService(captchaTimeOut));
        storageMap.put(Constant.CaptchaStorage.FIELD, new HiddenFieldsCaptchaService(captchaTimeOut));
    }
}

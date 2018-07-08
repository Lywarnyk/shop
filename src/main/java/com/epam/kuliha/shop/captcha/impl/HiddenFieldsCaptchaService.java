package com.epam.kuliha.shop.captcha.impl;

import com.epam.kuliha.shop.captcha.Captcha;
import com.epam.kuliha.shop.captcha.CaptchaCleaner;
import com.epam.kuliha.shop.captcha.CaptchaService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.epam.kuliha.shop.constant.Constant.CAPTCHA_ID;

public class HiddenFieldsCaptchaService implements CaptchaService {

    private static final Logger LOG = Logger.getLogger(HiddenFieldsCaptchaService.class);

    private Map<Long, Captcha> captchaMap;
    private long captchaTimeOut;

    public HiddenFieldsCaptchaService(long captchaTimeOut) {
        this.captchaMap = new ConcurrentHashMap<>();
        this.captchaTimeOut = captchaTimeOut;
    }

    @Override
    public void save(Captcha captcha, HttpServletRequest request, HttpServletResponse response) {
        captchaMap.put(captcha.getCreationDate(), captcha);
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request) {
        return captchaMap.get(Long.parseLong(request.getParameter(CAPTCHA_ID)));
    }

    @Override
    public boolean isHiddenField() {
        return true;
    }

    @Override
    public void initialise() {
        new CaptchaCleaner(captchaMap, captchaTimeOut);
        LOG.info("HiddenFieldsCaptchaService is initialised");
    }

    @Override
    public void delete(Captcha captcha) {
        if (captcha == null) {
            return;
        }
        captchaMap.remove(captcha.getCreationDate());
    }
}

package com.epam.kuliha.shop.captcha.impl;

import com.epam.kuliha.shop.captcha.Captcha;
import com.epam.kuliha.shop.captcha.CaptchaService;
import com.epam.kuliha.shop.constant.Constant;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionCaptchaService implements CaptchaService {

    private static final Logger LOG = Logger.getLogger(SessionCaptchaService.class);

    @Override
    public void save(Captcha captcha, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(Constant.RegisterFormFields.CAPTCHA, captcha);
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request) {
        return (Captcha) request.getSession().getAttribute(Constant.RegisterFormFields.CAPTCHA);
    }

    @Override
    public boolean isHiddenField() {
        return false;
    }

    @Override
    public void initialise() {
        LOG.info("SessionCaptchaService is initialised");
    }

    /**
     * Doesn't delete anything, because Session can contain only one captcha.
     * It will be replaced with new captcha, otherwise it will be deleted with session.
     * It doesn't effect on captcha expiration if during captcha validation you will check it's expiration.
     *
     * @param captcha captcha to be deleted
     */
    @Override
    public void delete(Captcha captcha) {
    }
}

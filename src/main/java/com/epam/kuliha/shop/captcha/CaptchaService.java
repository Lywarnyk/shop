package com.epam.kuliha.shop.captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Service for storage and manage captcha objects
 */
public interface CaptchaService {

    /**
     * Saves captcha in storage
     * @param captcha captcha to be saved
     */
    void save(Captcha captcha, HttpServletRequest request, HttpServletResponse response);

    /**
     * Gets captcha from storage
     * @param request needs to identify captcha by getting captcha id from it
     * @return captcha or null if storage doesn't contains captcha with id from request
     */
    Captcha getCaptcha(HttpServletRequest request);

    /**
     * @return Returns true if captcha id is storing in hidden fields
     * and false if it doesn't
     */
    boolean isHiddenField();

    /**
     * Initialization of service
     */
    void initialise();

    /**
     * Deleting captcha from storage
     * @param captcha to be deleted
     */
    void delete(Captcha captcha);
}

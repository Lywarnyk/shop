package com.epam.kuliha.shop.servlet;


import com.epam.kuliha.shop.captcha.Captcha;
import com.epam.kuliha.shop.captcha.CaptchaService;
import com.epam.kuliha.shop.constant.Constant;
import com.epam.kuliha.shop.exception.CaptchaException;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CaptchaServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOG.debug("CaptchaServlet#doGet()");

        CaptchaService captchaService = (CaptchaService) getServletContext().getAttribute(Constant.CAPTCHA_SERVICE);
        Captcha captcha = captchaService.getCaptcha(req);

        if (captcha == null) {
            LOG.warn("captcha is expired or does not exist");
            throw new CaptchaException();
        }

        ImageIO.write(captcha.getImage(), "jpeg", resp.getOutputStream());

        LOG.debug("doGet method ends");
    }
}

package com.epam.kuliha.shop.servlet;

import com.epam.kuliha.shop.bean.RegisterFormBean;
import com.epam.kuliha.shop.captcha.Captcha;
import com.epam.kuliha.shop.captcha.CaptchaGenerator;
import com.epam.kuliha.shop.captcha.CaptchaService;
import com.epam.kuliha.shop.entity.User;
import com.epam.kuliha.shop.exception.ExistentUserException;
import com.epam.kuliha.shop.service.image.ImageService;
import com.epam.kuliha.shop.service.user.UserService;
import com.epam.kuliha.shop.service.validator.CaptchaValidator;
import com.epam.kuliha.shop.service.validator.RegisterFormBeanValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Map;

import static com.epam.kuliha.shop.constant.Constant.BEAN;
import static com.epam.kuliha.shop.constant.Constant.ERRORS;
import static com.epam.kuliha.shop.constant.Constant.IMAGE_SERVICE;
import static com.epam.kuliha.shop.constant.Constant.CAPTCHA_SERVICE;
import static com.epam.kuliha.shop.constant.Constant.CAPTCHA_ID;
import static com.epam.kuliha.shop.constant.Constant.CAPTCHA_GENERATOR;
import static com.epam.kuliha.shop.constant.Constant.CAPTCHA_TIME_OUT;
import static com.epam.kuliha.shop.constant.Constant.Path.REGISTER;
import static com.epam.kuliha.shop.constant.Constant.Path.SUCCESS_PAGE;
import static com.epam.kuliha.shop.constant.Constant.RegisterFormFields.CAPTCHA;
import static com.epam.kuliha.shop.constant.Constant.RegisterFormFields.EMAIL;
import static com.epam.kuliha.shop.constant.Constant.USER_SERVICE;
import static com.epam.kuliha.shop.constant.Constant.USER;
import static com.epam.kuliha.shop.constant.Constant.Path.REGISTER_JSP;
import static com.epam.kuliha.shop.constant.Constant.RegisterFormFields.AVATAR;

@MultipartConfig
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(RegisterServlet.class);
    private RegisterFormBeanValidator registerFormBeanValidator;
    private CaptchaValidator captchaValidator;

    @Override
    public void init() {
        registerFormBeanValidator = new RegisterFormBeanValidator();
        captchaValidator = new CaptchaValidator();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOG.debug("doGet method starts");

        HttpSession session = req.getSession();
        Object errors = session.getAttribute(ERRORS);
        Object bean = session.getAttribute(BEAN);
        if (errors != null && bean != null) {
            req.setAttribute(BEAN, bean);
            req.setAttribute(ERRORS, errors);
            session.removeAttribute(BEAN);
            session.removeAttribute(ERRORS);
        }

        CaptchaService captchaService = (CaptchaService) getServletContext().getAttribute(CAPTCHA_SERVICE);
        Captcha captcha = generateCaptcha();
        captchaService.save(captcha, req, resp);

        LOG.debug("CaptchaId to be set: " + captcha.getCreationDate());

        req.setAttribute(CAPTCHA_ID, captcha.getCreationDate());
        resp.setHeader("Cache-Control", "no-cache");
        req.getRequestDispatcher(REGISTER_JSP).forward(req, resp);

        LOG.debug("doGet method ends");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOG.debug("doPost method starts");

        UserService userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
        RegisterFormBean bean = RegisterFormBean.from(req);
        Map<String, String> errors = registerFormBeanValidator.validate(bean);
        checkCaptcha(req, errors);
        try {
            if (errors.isEmpty()) {
                User user = bean.toUser();
                userService.save(user);
                req.getSession().setAttribute(USER, user);
                saveAvatar(req, user);
            }
        } catch (ExistentUserException e) {
            errors.put(EMAIL, "Specified email is already registered");
        }

        if (errors.isEmpty()) {
            req.getRequestDispatcher(SUCCESS_PAGE).forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute(ERRORS, errors);
            bean.resetPasswords();
            session.setAttribute(BEAN, bean);
            resp.sendRedirect(REGISTER);
        }

        LOG.debug("doPost method ends");
    }

    private Captcha generateCaptcha() {
        return ((CaptchaGenerator) getServletContext().getAttribute(CAPTCHA_GENERATOR)).generate();
    }

    private void checkCaptcha(HttpServletRequest req, Map<String, String> errors) {
        long captchaTimeOut = (long) getServletContext().getAttribute(CAPTCHA_TIME_OUT);
        String captchaValue = req.getParameter(CAPTCHA);
        CaptchaService captchaService = ((CaptchaService) getServletContext().getAttribute(CAPTCHA_SERVICE));
        Captcha captcha = captchaService.getCaptcha(req);
        captchaValidator.validate(captcha, captchaTimeOut, captchaValue, errors);
        captchaService.delete(captcha);
    }

    private void saveAvatar(HttpServletRequest req, User user) throws IOException, ServletException {
        Part image = req.getPart(AVATAR);
        if (image.getSize() > 0) {
            ((ImageService) getServletContext().getAttribute(IMAGE_SERVICE)).save(image, String.valueOf(user.getId()));
        }
    }
}

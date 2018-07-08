package com.epam.kuliha.shop.servlet;


import com.epam.kuliha.shop.entity.User;
import com.epam.kuliha.shop.exception.UserNotFoundException;
import com.epam.kuliha.shop.security.Password;
import com.epam.kuliha.shop.service.user.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.kuliha.shop.constant.Constant.ERROR_MESSAGE;
import static com.epam.kuliha.shop.constant.Constant.Error.LOGIN_OR_PASSWORD_IS_INCORRECT;
import static com.epam.kuliha.shop.constant.Constant.Path.ERROR;
import static com.epam.kuliha.shop.constant.Constant.Path.REFERER;
import static com.epam.kuliha.shop.constant.Constant.RegisterFormFields.PASSWORD;
import static com.epam.kuliha.shop.constant.Constant.USER;
import static com.epam.kuliha.shop.constant.Constant.USER_SERVICE;
import static com.epam.kuliha.shop.constant.DBConstant.Column.EMAIL;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("doGet starts");

        req.getSession().invalidate();
        sendRedirectToPreviousPage(req, resp);

        LOG.debug("doGet finishes");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("doGet starts");

        UserService userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
        String password = req.getParameter(PASSWORD);
        String email = req.getParameter(EMAIL);

        try {
            User user = userService.getUser(email);
            if (user.getPassword().equals(Password.hash(password))) {
                user.resetPassword();
                req.getSession().setAttribute(USER, user);
                sendRedirectToPreviousPage(req, resp);
            } else
                sendError(LOGIN_OR_PASSWORD_IS_INCORRECT, req, resp);
        } catch (UserNotFoundException e) {
            LOG.error(e + ": " + email);
            sendError(LOGIN_OR_PASSWORD_IS_INCORRECT, req, resp);
        }

        LOG.debug("doGet finishes");
    }

    private void sendError(String message, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().setAttribute(ERROR_MESSAGE, message);
        resp.sendRedirect(ERROR);
    }

    private void sendRedirectToPreviousPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getHeader(REFERER));
    }
}
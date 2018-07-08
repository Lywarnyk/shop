package com.epam.kuliha.shop.servlet;

import com.epam.kuliha.shop.bean.RegisterFormBean;
import com.epam.kuliha.shop.captcha.Captcha;
import com.epam.kuliha.shop.captcha.CaptchaGenerator;
import com.epam.kuliha.shop.captcha.CaptchaService;
import com.epam.kuliha.shop.constant.Constant;
import com.epam.kuliha.shop.entity.User;
import com.epam.kuliha.shop.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.epam.kuliha.shop.constant.Constant.Path.REGISTER;
import static com.epam.kuliha.shop.constant.Constant.Path.REGISTER_JSP;
import static com.epam.kuliha.shop.constant.Constant.Path.SUCCESS_PAGE;
import static com.epam.kuliha.shop.constant.Constant.ERRORS;
import static com.epam.kuliha.shop.constant.Constant.BEAN;
import static com.epam.kuliha.shop.constant.Constant.CAPTCHA_ID;
import static com.epam.kuliha.shop.constant.Constant.RegisterFormFields.*;
import static com.epam.kuliha.shop.constant.Constant.USER_SERVICE;
import static com.epam.kuliha.shop.constant.Constant.CAPTCHA_TIME_OUT;
import static com.epam.kuliha.shop.constant.Constant.CAPTCHA_SERVICE;
import static com.epam.kuliha.shop.constant.Constant.CAPTCHA_GENERATOR;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RegisterServletTest {

    RegisterServlet servlet;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpServletRequest request;
    @Mock
    CaptchaGenerator captchaGenerator;
    @Mock
    HttpSession session;
    @Mock
    UserService userService;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    Captcha captcha;
    @Mock
    CaptchaService captchaService;
    @Mock
    ServletContext servletContext;
    @Mock
    ServletConfig servletConfig;
    @Mock
    RegisterFormBean bean;
    @Mock
    Part image;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        servlet = new RegisterServlet();
        servlet.init(servletConfig);
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(USER_SERVICE)).thenReturn(userService);
        when(servletContext.getAttribute(CAPTCHA_TIME_OUT)).thenReturn(5L);
        when(servletContext.getAttribute(CAPTCHA_SERVICE)).thenReturn(captchaService);
        when(servletContext.getAttribute(CAPTCHA_GENERATOR)).thenReturn(captchaGenerator);
        when(request.getSession()).thenReturn(session);
        when(captchaGenerator.generate()).thenReturn(captcha);
        when(request.getParameter(FIRST_NAME)).thenReturn("Test");
        when(request.getParameter(LAST_NAME)).thenReturn("Test");
        when(request.getParameter(EMAIL)).thenReturn("test@rich.com");
        when(request.getParameter(PASSWORD)).thenReturn("Test");
        when(request.getParameter(RE_PASSWORD)).thenReturn("Test");
        when(request.getParameter(MAILING)).thenReturn("true");
        when(session.getAttribute(CAPTCHA)).thenReturn(captcha);
        when(captchaService.getCaptcha(request)).thenReturn(captcha);
        when(captcha.getValue()).thenReturn("CaptchaTest");
    }

    @Test
    public void doGet_FirstPageVisitErrorsAndBeanAreNull_SpecifiedMethodsShouldBeCalled() throws ServletException, IOException {
        when(captcha.getCreationDate()).thenReturn(100L);
        when(request.getRequestDispatcher(REGISTER_JSP)).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute(Constant.CAPTCHA_ID, 100L);
        verify(dispatcher).forward(request, response);
        verify(captchaService).save(captcha, request, response);
    }

    @Test
    public void doGet_ErrorsMapNotEmpty_SpecifiedMethodsShouldBeCalledAndSetMapToRequest() throws ServletException, IOException {
        when(captcha.getCreationDate()).thenReturn(100L);
        Map<String, String> errors = new HashMap<>();
        errors.put("Test", "Some error message");
        when(session.getAttribute(ERRORS)).thenReturn(errors);
        when(session.getAttribute(BEAN)).thenReturn(bean);
        when(request.getRequestDispatcher(REGISTER_JSP)).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute(CAPTCHA_ID, 100L);
        verify(request).setAttribute(ERRORS, errors);
        verify(request).setAttribute(BEAN, bean);
        verify(session).removeAttribute(ERRORS);
        verify(session).removeAttribute(BEAN);
        verify(captchaService).save(captcha, request, response);
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void doPost_CorrectCaptcha_ShouldSave() throws ServletException, IOException {
        when(request.getPart(AVATAR)).thenReturn(image);
        when(image.getSize()).thenReturn(0L);
        when(request.getParameter(CAPTCHA)).thenReturn("CaptchaTest");
        when(request.getRequestDispatcher(SUCCESS_PAGE)).thenReturn(dispatcher);
        servlet.doPost(request, response);

        verify(userService).save(new User("Test", "Test", "test@rich.com","Test", true));
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void doPost_IncorrectCaptcha_ShouldSetErrorsMapToSessionWithSpecifiedMessage() throws ServletException, IOException {
        when(request.getParameter(CAPTCHA)).thenReturn("IncorrectCaptchaTest");
        Map<String, String> errors = new HashMap<>();
        errors.put(CAPTCHA, "You supposed to be a robot!?");
        when(captcha.isExpired(5L)).thenReturn(false);

        servlet.doPost(request, response);

        verify(session).setAttribute(ERRORS, errors);
        verify(response).sendRedirect(REGISTER);
    }
}

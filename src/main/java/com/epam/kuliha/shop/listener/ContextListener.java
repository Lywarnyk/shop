package com.epam.kuliha.shop.listener;

import com.epam.kuliha.shop.builder.SQLBuilder;
import com.epam.kuliha.shop.captcha.CaptchaGenerator;
import com.epam.kuliha.shop.captcha.CaptchaService;
import com.epam.kuliha.shop.captcha.CaptchaServiceFactory;
import com.epam.kuliha.shop.constant.Constant;
import com.epam.kuliha.shop.db.ConnectionHandler;
import com.epam.kuliha.shop.db.ConnectionPool;
import com.epam.kuliha.shop.db.TransactionManager;
import com.epam.kuliha.shop.repository.OrderRepository;
import com.epam.kuliha.shop.repository.TransportRepository;
import com.epam.kuliha.shop.repository.UserRepository;
import com.epam.kuliha.shop.repository.impl.DBOrderRepository;
import com.epam.kuliha.shop.repository.impl.DBTransportRepository;
import com.epam.kuliha.shop.repository.impl.DBUserRepository;
import com.epam.kuliha.shop.service.image.ImageService;
import com.epam.kuliha.shop.service.order.OrderService;
import com.epam.kuliha.shop.service.order.impl.DBOrderService;
import com.epam.kuliha.shop.service.transport.TransportService;
import com.epam.kuliha.shop.service.transport.impl.DBTransportService;
import com.epam.kuliha.shop.service.user.UserService;
import com.epam.kuliha.shop.service.user.impl.DBUserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;

import static com.epam.kuliha.shop.constant.Constant.CAPTCHA_SERVICE;
import static com.epam.kuliha.shop.constant.Constant.CAPTCHA_TIME_OUT;
import static com.epam.kuliha.shop.constant.Constant.CAPTCHA_GENERATOR;
import static com.epam.kuliha.shop.constant.Constant.USER_SERVICE;
import static com.epam.kuliha.shop.constant.Constant.Path.IMAGES;
import static com.epam.kuliha.shop.constant.Constant.TRANSPORT_SERVICE;
import static com.epam.kuliha.shop.constant.Constant.ORDER_SERVICE;

@WebListener
public class ContextListener implements ServletContextListener {

    private static final int MILLISECONDS = 1000;
    private static final int SECONDS = 60;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();

        ConnectionHandler connectionHandler = new ConnectionHandler();
        TransactionManager transactionManager = new TransactionManager(connectionHandler, new ConnectionPool());
        setUserService(context, transactionManager, connectionHandler);
        setTransportService(context, transactionManager, connectionHandler);
        setOrderService(context, transactionManager, connectionHandler);
        setCaptchaSettings(context);
        setImageService(context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

    private void setOrderService(ServletContext context, TransactionManager transactionManager, ConnectionHandler connectionHandler) {
        OrderRepository orderRepository = new DBOrderRepository(connectionHandler);
        OrderService orderService = new DBOrderService(transactionManager, orderRepository);
        context.setAttribute(ORDER_SERVICE, orderService);
    }

    private void setTransportService(ServletContext context, TransactionManager transactionManager, ConnectionHandler connectionHandler) {
        TransportRepository transportRepository = new DBTransportRepository(connectionHandler);
        TransportService transportService = new DBTransportService(transactionManager, transportRepository);
        context.setAttribute(TRANSPORT_SERVICE, transportService);
    }

    private void setUserService(ServletContext context, TransactionManager transactionManager, ConnectionHandler connectionHandler) {
        UserRepository userRepository = new DBUserRepository(connectionHandler);
        UserService userService = new DBUserService(transactionManager, userRepository);
        context.setAttribute(USER_SERVICE, userService);
    }

    private void setCaptchaSettings(ServletContext context) {
        CaptchaGenerator captchaGenerator = new CaptchaGenerator();
        context.setAttribute(CAPTCHA_GENERATOR, captchaGenerator);

        long captchaTimeOut = Long.parseLong(context.getInitParameter(CAPTCHA_TIME_OUT)) * MILLISECONDS * SECONDS;
        context.setAttribute(CAPTCHA_TIME_OUT, captchaTimeOut);

        String storageParam = context.getInitParameter(CAPTCHA_SERVICE);
        CaptchaService captchaService = new CaptchaServiceFactory(captchaTimeOut).getCaptchaStorage(storageParam);
        captchaService.initialise();
        context.setAttribute(CAPTCHA_SERVICE, captchaService);
    }

    private void setImageService(ServletContext context) {
        File file = new File((new File(context.getRealPath(File.separator)).getParent() + IMAGES));
        if(!file.exists()){
            file.mkdir();
        }
        String imageFolder = file.getAbsolutePath() + File.separator;
        context.setAttribute(Constant.IMAGE_SERVICE, new ImageService(imageFolder));
    }
}

package com.epam.kuliha.shop.servlet;

import com.epam.kuliha.shop.bean.PaymentInfoBean;
import com.epam.kuliha.shop.entity.User;
import com.epam.kuliha.shop.security.Card;
import com.epam.kuliha.shop.service.cart.Cart;
import com.epam.kuliha.shop.service.order.Order;
import com.epam.kuliha.shop.service.order.OrderService;
import com.epam.kuliha.shop.service.validator.PaymentInfoBeanValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.kuliha.shop.constant.Constant.CART;
import static com.epam.kuliha.shop.constant.Constant.ORDER_SERVICE;
import static com.epam.kuliha.shop.constant.Constant.Path.INDEX_JSP;
import static com.epam.kuliha.shop.constant.Constant.Path.REFERER;
import static com.epam.kuliha.shop.constant.Constant.Path.REGISTER;
import static com.epam.kuliha.shop.constant.Constant.USER;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private static final String PAY_TYPE = "payType";
    private static final String DELIVERY = "delivery";
    private static final String DESCRIPTION = "description";
    private static final String CARD_NUMBER = "cardNumber";
    private static final String ORDER_SUCCESS_JSP = "orderSuccess.jsp";
    private static final String ORDER = "order";

    private OrderService orderService;
    private PaymentInfoBeanValidator validator;

    @Override
    public void init() throws ServletException {
        orderService = (OrderService) getServletContext().getAttribute(ORDER_SERVICE);
        validator = new PaymentInfoBeanValidator();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute(USER) == null)
            resp.sendRedirect(REGISTER);

        Cart cart = (Cart) req.getSession().getAttribute(CART);
        User user = (User) req.getSession().getAttribute(USER);

        PaymentInfoBean paymentInfoBean = getPaymentInfoBean(req);
        if (validator.isValid(paymentInfoBean)) {
            paymentInfoBean.setCardNumber(Card.hideCardNumber(paymentInfoBean.getCardNumber()));
            Order order = createOrder(paymentInfoBean, cart, user);
            orderService.add(order);
            cart.clear();
            req.getSession().setAttribute(ORDER, order);
            resp.sendRedirect(ORDER);
        } else
            resp.sendRedirect(req.getHeader(REFERER));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute(ORDER)!=null) {
            req.setAttribute(ORDER, session.getAttribute(ORDER));
            session.removeAttribute(ORDER);
            req.getRequestDispatcher(ORDER_SUCCESS_JSP).forward(req, resp);
        } else
            req.getRequestDispatcher(INDEX_JSP).forward(req, resp);
    }

    private Order createOrder(PaymentInfoBean paymentInfoBean, Cart cart, User user) {
        Order order = new Order(user, cart.getOrderItems());
        order.setDeliveryInfo(paymentInfoBean.getDescription());
        order.setDelivery(paymentInfoBean.getDelivery());
        order.setPayType(paymentInfoBean.getPayType());
        order.setCardNumber(paymentInfoBean.getCardNumber());
        return order;
    }

    private PaymentInfoBean getPaymentInfoBean(HttpServletRequest req) {
        PaymentInfoBean paymentInfoBean = new PaymentInfoBean();
        paymentInfoBean.setPayType(req.getParameter(PAY_TYPE));
        paymentInfoBean.setDelivery(req.getParameter(DELIVERY));
        paymentInfoBean.setDescription(req.getParameter(DESCRIPTION));
        paymentInfoBean.setCardNumber(req.getParameter(CARD_NUMBER));
        return paymentInfoBean;
    }
}

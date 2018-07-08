package com.epam.kuliha.shop.servlet;

import com.epam.kuliha.shop.bean.CartBean;
import com.epam.kuliha.shop.bean.JSONRequestBean;
import com.epam.kuliha.shop.constant.Constant;
import com.epam.kuliha.shop.entity.Transport;
import com.epam.kuliha.shop.service.cart.Cart;
import com.epam.kuliha.shop.service.order.OrderItem;
import com.epam.kuliha.shop.service.transport.TransportService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.epam.kuliha.shop.constant.Constant.CART;
import static com.epam.kuliha.shop.constant.Constant.TRANSPORT_SERVICE;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CartServlet.class);

    private static final int START_AMOUNT = 1;
    private static final String ORDER_ITEMS = "orderItems";
    private static final String TOTAL_PRICE = "totalPrice";
    private TransportService transportService;

    @Override
    public void init() throws ServletException {
        transportService = (TransportService) getServletContext().getAttribute(TRANSPORT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute(CART);
        List<OrderItem> orderItems = cart.getOrderItems();
        int totalPrice = cart.getTotalPrice();

        req.setAttribute(ORDER_ITEMS, orderItems);
        req.setAttribute(TOTAL_PRICE, totalPrice);

        req.getRequestDispatcher("order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int transportId = Integer.parseInt(req.getParameter(Constant.TRANSPORT_ID));
        Cart cart = (Cart) req.getSession().getAttribute(CART);
        Transport transport = transportService.getTransportById(transportId);
        cart.add(transport, START_AMOUNT);
        sendResponse(cart, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute(CART);
        try {
            JSONRequestBean bean = getJSONRequestBeanFromRequest(req);
            int transportId = Integer.parseInt(bean.getTransportId());
            int transportAmount = Integer.parseInt(bean.getTransportAmount());
            if (transportAmount > 0) {
                Transport transport = transportService.getTransportById(transportId);
                cart.set(transport, transportAmount);
            }
            if (transportAmount == 0){
                cart.remove(transportId);
            }
        } catch (JsonSyntaxException e) {
            LOG.debug(e);
        }
        sendResponse(cart, resp);
    }

    private JSONRequestBean getJSONRequestBeanFromRequest(HttpServletRequest req) throws IOException {
        TypeAdapter<JSONRequestBean> adapter = new Gson().getAdapter(JSONRequestBean.class);
        return adapter.fromJson(new InputStreamReader(req.getInputStream()));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute(CART);
        int transportId = Integer.parseInt(req.getParameter(Constant.TRANSPORT_ID));
        cart.remove(transportId);
        sendResponse(cart, resp);
    }

    private void sendResponse(Cart cart, HttpServletResponse resp) throws IOException {
        CartBean cartBean = new CartBean(cart.getTotalPrice(), cart.getProductsAmount());
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        String json = gson.toJson(cartBean);
        resp.getWriter().write(json);
    }
}

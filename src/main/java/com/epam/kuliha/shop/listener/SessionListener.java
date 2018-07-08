package com.epam.kuliha.shop.listener;

import com.epam.kuliha.shop.service.cart.Cart;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import static com.epam.kuliha.shop.constant.Constant.CART;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        Cart cart  = new Cart();
        se.getSession().setAttribute(CART, cart);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //no actions needed
    }
}

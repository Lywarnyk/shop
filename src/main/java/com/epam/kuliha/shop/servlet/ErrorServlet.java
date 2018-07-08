package com.epam.kuliha.shop.servlet;

import com.epam.kuliha.shop.constant.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute(Constant.ERROR_MESSAGE) != null){
            req.setAttribute(Constant.ERROR_MESSAGE, session.getAttribute(Constant.ERROR_MESSAGE));
            session.removeAttribute(Constant.ERROR_MESSAGE);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }else
            resp.sendRedirect("index");
    }
}

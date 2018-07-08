package com.epam.kuliha.shop.filter;


import com.epam.kuliha.shop.entity.User;
import com.epam.kuliha.shop.security.AccessController;
import com.epam.kuliha.shop.security.xml.entity.Constraint;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.epam.kuliha.shop.constant.Constant.ERROR_MESSAGE;
import static com.epam.kuliha.shop.constant.Constant.Path.ERROR;
import static com.epam.kuliha.shop.constant.Constant.Path.REGISTER;
import static com.epam.kuliha.shop.constant.Constant.USER;

public class RoleFilter implements Filter {

    private static final String SECURITY_FILE = "security-file";
    private AccessController accessController;

    @Override
    public void init(FilterConfig filterConfig) {
        String securityFile = filterConfig.getInitParameter(SECURITY_FILE);
        accessController = new AccessController(securityFile);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Object user = ((HttpServletRequest) request).getSession().getAttribute(USER);
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        Optional<Constraint> constraintOptional = accessController.getConstraintByUrl(req.getRequestURI());
        if (constraintOptional.isPresent()){
            if (user == null) {
                resp.sendRedirect(REGISTER);
                return;
            } else if (!constraintOptional.get().getRoles().contains(((User)user).getRole())) {
                req.getSession().setAttribute(ERROR_MESSAGE, "Access denied. Login or register first.");
                req.getRequestDispatcher(ERROR).forward(req, resp);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //no action needed
    }
}

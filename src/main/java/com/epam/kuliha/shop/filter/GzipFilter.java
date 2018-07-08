package com.epam.kuliha.shop.filter;

import com.epam.kuliha.shop.gzip.GZIPResponseWrapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class GzipFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String acceptEncoding = request.getHeader("accept-encoding");
        if (acceptEncoding != null && acceptEncoding.contains("gzip")) {
            GZIPResponseWrapper wrappedResponse = new GZIPResponseWrapper(response);
            chain.doFilter(req, wrappedResponse);
            wrappedResponse.finishResponse();
            return;
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        //no action needed
    }
}

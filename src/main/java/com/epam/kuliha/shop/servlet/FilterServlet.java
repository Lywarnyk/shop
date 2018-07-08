package com.epam.kuliha.shop.servlet;

import com.epam.kuliha.shop.builder.FilterBuilder;
import com.epam.kuliha.shop.entity.Category;
import com.epam.kuliha.shop.entity.Filter;
import com.epam.kuliha.shop.entity.Manufacturer;
import com.epam.kuliha.shop.entity.Transport;
import com.epam.kuliha.shop.service.transport.TransportService;
import com.epam.kuliha.shop.service.validator.FilterValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.epam.kuliha.shop.constant.Constant.Filter.*;
import static com.epam.kuliha.shop.constant.Constant.Path.ALL_OFFERS_JSP;
import static com.epam.kuliha.shop.constant.Constant.TRANSPORT_SERVICE;

@WebServlet("/allOffers")
public class FilterServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(FilterServlet.class);

    private FilterValidator filterValidator;
    private TransportService transportService;

    @Override
    public void init() throws ServletException {
        filterValidator = new FilterValidator();
        transportService = (TransportService) getServletContext().getAttribute(TRANSPORT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOG.debug("FilterServlet starts");

        Filter filter = createFilterBean(req);

        List<Manufacturer> manufacturers = transportService.getAllManufacturers();
        List<Category> categories = transportService.getAllCategories();
        int amount = transportService.getTransportCountByFilter(filter);
        List<Transport> transports = null;
        int pagesAmount = (int)Math.ceil((double)amount / filter.getItemsPerPage());
        if (amount > 0 && pagesAmount >= filter.getPage()) {
            transports = transportService.getTransportByFilter(filter);
        }

        req.setAttribute("manufacturers", manufacturers);
        req.setAttribute("categories", categories);
        req.setAttribute("transports", transports);
        req.setAttribute("filter", filter);
        req.setAttribute("pagesAmount", pagesAmount);

        req.getRequestDispatcher(ALL_OFFERS_JSP).forward(req, resp);

        LOG.debug("FilterServlet ends");
    }

    private Filter createFilterBean(HttpServletRequest req) {
        return new FilterBuilder(filterValidator)
                .setPrice(req.getParameter(MIN_PRICE), req.getParameter(MAX_PRICE))
                .setCategories(req.getParameterValues(CATEGORIES))
                .setName(req.getParameter(NAME))
                .setManufacturer(req.getParameter(MANUFACTURER))
                .setItemsPerPage(req.getParameter(ITEMS))
                .setSort(req.getParameter(SORT))
                .setOrder(req.getParameter(ORDER))
                .setPage(req.getParameter(PAGE))
                .build();
    }
}

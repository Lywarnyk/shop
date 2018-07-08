package com.epam.kuliha.shop.tag;

import com.epam.kuliha.shop.captcha.CaptchaService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

import static com.epam.kuliha.shop.constant.Constant.CAPTCHA_ID;
import static com.epam.kuliha.shop.constant.Constant.CAPTCHA_SERVICE;

public class CaptchaTag extends SimpleTagSupport {

    private static final Logger LOG = Logger.getLogger(CaptchaTag.class);

    @Override
    public void doTag() throws JspException, IOException {

        LOG.debug("Custom tag starts");

        PageContext pageContext = (PageContext)getJspContext();
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        HttpSession session = request.getSession();
        ServletContext servletContext = session.getServletContext();

        CaptchaService captchaService = (CaptchaService) servletContext.getAttribute(CAPTCHA_SERVICE);
        long captchaId = (long) request.getAttribute(CAPTCHA_ID);

        JspWriter out = getJspContext().getOut();
        out.write(StringUtils.join("<img src=\"captcha?captchaId=", captchaId, "\">"));
        out.write("<p> Type symbols from picture</p>");
        out.write("<input type=\"text\" name=\"captcha\" required>");

        if(captchaService.isHiddenField()){
            out.write("<input type=\"hidden\" name=\"captchaId\" value=\""+ captchaId + "\">");
        }
        out.flush();

        LOG.debug("Custom tag ends");
    }
}

package com.epam.kuliha.shop.tag;


import com.epam.kuliha.shop.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

import static com.epam.kuliha.shop.constant.Constant.USER;

public class UserSignInTag extends SimpleTagSupport {
    private static final Logger LOG = Logger.getLogger(UserSignInTag.class);

    @Override
    public void doTag() throws JspException, IOException {
        LOG.debug("doTag starts");

        HttpSession session = obtainSession();
        Object user = session.getAttribute(USER);
        JspWriter out = getJspContext().getOut();

        if (user == null) {
            setSignInBlock(out);
        } else {
            setUserInfoBlock((User) user, out);
        }

        LOG.debug("doTag finished");
    }

    private HttpSession obtainSession() {
        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        return request.getSession();
    }

    private void setSignInBlock(JspWriter out) throws IOException {
        out.write("<form action=\"login\" class=\"navbar-form navbar-left\" method=\"post\">\n" +
                "<input class=\"form-control\" type=\"email\" name=\"email\" placeholder=\"Email\">\n" +
                "<input class=\"form-control\" type=\"password\" name=\"password\" placeholder=\"Password\" required>\n" +
                "<button type=\"submit\" class=\"btn btn-default\" type=\"submit\">Sign in</button>\n" +
                "</form>");
        out.flush();
    }

    private void setUserInfoBlock(User user, JspWriter out) throws IOException {
        out.write("<form action=\"login\" class=\"navbar-form navbar-left\">");
        out.write(StringUtils.join("<img src=\"avatar?userId=", user.getId(), "\"", " class=\"avatar\" \n>"));
        out.write(StringUtils.join("<label class=\"user-name\">", user.getFirstName(), " ", user.getLastName(), " </label>\n"));
        out.write("<button type=\"submit\" class=\"btn btn-default\" type=\"submit\">Exit</button>\n</form>");
        out.flush();
    }
}

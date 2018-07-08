package com.epam.kuliha.shop.servlet;

import com.epam.kuliha.shop.constant.Constant;
import com.epam.kuliha.shop.service.image.ImageService;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;

@WebServlet("/avatar")
public class AvatarServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(AvatarServlet.class);
    private File defaultAvatar;

    @Override
    public void init() {
        defaultAvatar = new File("src/main/webapp/images/default-avatar.png");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.debug("doGet starts");

        String userId = req.getParameter(Constant.USER_ID);
        File ava = ((ImageService) getServletContext().getAttribute(Constant.IMAGE_SERVICE)).get(userId);
        if (!ava.exists()) {
            ava = defaultAvatar;
        }
        OutputStream out = resp.getOutputStream();
        try (InputStream in = new FileInputStream(ava)) {
            byte[] buffer = new byte[4096];
            int byteRead;
            while ((byteRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, byteRead);
            }
        }

        LOG.debug("doGet starts");
    }
}

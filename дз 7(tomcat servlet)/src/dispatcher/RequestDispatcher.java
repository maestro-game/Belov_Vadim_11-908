package dispatcher;

import html.HtmlGenerator;
import html.Page;
import models.User;
import utils.LoginManager;
import utils.RegisterManager;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class RequestDispatcher implements Dispatcher {
    private HtmlGenerator htmlGenerator;
    private LoginManager loginManager;
    private RegisterManager registerManager;


    public void dispatch(HttpServletRequest request, HttpServletResponse response) {

        Writer writer;

        try {
            writer = response.getWriter();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        Map<String, Object> root = new HashMap<>();
        ServletContext servletContext = request.getServletContext();
        String uri = request.getRequestURI();

        if (uri.equals("/")) {
            htmlGenerator.render(Page.home, servletContext, writer, root);
            return;
        }

        String[] req = uri.split("/");
        Page page;

        try {
            page = Page.valueOf(req[1]);
        } catch (IllegalArgumentException e) {
            htmlGenerator.render(Page.notFound, servletContext, writer, root);
            return;
        }

        String parameter = null;

        switch (page) {
            case id -> {
                if (req.length < 3) {
                    page = Page.notFound;
                } else {
                    parameter = req[2];
                }
            }
            case login -> {

            }
            case register -> {
                User user;
                try {
                    user = new User(
                            request.getParameter("login"),
                            request.getParameter("name"),
                            request.getParameter("surname"),
                            new Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birth")).getTime()),
                            Byte.parseByte(request.getParameter("course")),
                            request.getParameter("group"));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }

                if (registerManager.register(user, request.getParameter("password"))) {
                    parameter = request.getParameter("login");
                } else {
                    root.put("login", user.getId());
                    root.put("name", user.getName());
                    root.put("surname", user.getSurname());
                    root.put("birth", user.getBirth());
                    root.put("course", user.getCourse());
                    root.put("group", user.getGroup());
                    page = Page.register;
                }
            }
        }

        htmlGenerator.render(page, parameter, servletContext, writer, root);
    }

    public RequestDispatcher(HtmlGenerator htmlGenerator, LoginManager loginManager, RegisterManager registerManager) {
        this.htmlGenerator = htmlGenerator;
        this.loginManager = loginManager;
        this.registerManager = registerManager;
    }
}

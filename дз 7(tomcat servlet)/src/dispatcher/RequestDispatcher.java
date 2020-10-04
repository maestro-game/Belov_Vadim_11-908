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

        if (req.length < 1) {
            htmlGenerator.render(Page.notFound, servletContext, writer, root);
            return;
        }

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
                if (request.getMethod().equals("GET")) {
                    htmlGenerator.render(Page.register, servletContext, writer, root);
                    return;
                }
                Date birth;
                try {
                    String s = request.getParameter("birth");
                    birth = s.equals("") ? null : new Date(new SimpleDateFormat("yyyy-MM-dd").parse(s).getTime());
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
                Byte course;
                {
                    String s = request.getParameter("course");
                    course = s.equals("") ? null : Byte.parseByte(s);
                }
                String login = request.getParameter("login");
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String group = request.getParameter("group");
                String password = request.getParameter("password");

                int answer;
                if (birth != null && course != null && login.length() > 0 && name.length() > 0 && surname.length() > 0 && group.length() > 0 && password.length() > 0) {
                    if (password.length() < 12) {
                        answer = 200;
                    } else {
                        answer = registerManager.register(new User(login, name, surname, birth, course, group), password);
                    }
                } else {
                    answer = 23502;
                }
                if (answer == 0) {
                    page = Page.id;
                    parameter = login;
                } else {
                    switch (answer) {
                        case 200 -> root.put("message", "Пароль должен быть не короче 12 символов.");
                        case 23502 -> root.put("message", "Все поля должны быть заполнены.");
                        case 23505 -> root.put("message", "Пользователь с таким логином уже существует.");
                    }
                    root.put("login", login);
                    root.put("name", name);
                    root.put("surname", surname);
                    root.put("birth", birth);
                    root.put("course", course);
                    root.put("group", group);
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

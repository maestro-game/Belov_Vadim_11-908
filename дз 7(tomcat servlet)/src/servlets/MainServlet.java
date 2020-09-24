package servlets;

import data.DataSource;
import dispatcher.Dispatcher;
import data.SimpleDataSource;
import dispatcher.RequestDispatcher;
import html.HTMLManager;
import html.HtmlGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class MainServlet extends HttpServlet {
    private static String URL = "jdbc:postgresql://localhost:5432/social";
    private static String USER = "postgres";
    private static String PASSWORD = "sdfsdf";
    HtmlGenerator htmlManager;

    @Override
    public void init() {
        System.out.println("hi martin");
        DataSource dataSource;
        try {
            dataSource = new SimpleDataSource(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        htmlManager = new HTMLManager(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Dispatcher dispatcher = new RequestDispatcher(htmlManager);

        try {
            dispatcher.dispatch(req.getRequestURI(), resp.getWriter());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

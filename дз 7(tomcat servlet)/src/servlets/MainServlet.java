package servlets;

import data.DataManager;
import data.SimpleDataManager;
import dispatcher.Dispatcher;
import dispatcher.RequestDispatcher;
import html.HTMLManager;
import html.HtmlGenerator;
import utils.LoginManager;
import utils.RegisterManager;
import utils.SimpleLoginManager;
import utils.SimpleRegisterManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class MainServlet extends HttpServlet {
    HtmlGenerator htmlManager;
    RegisterManager registerManager;
    LoginManager loginManager;
    Dispatcher dispatcher;

    @Override
    public void init() {
        DataManager dataManager;
        try {
            dataManager = new SimpleDataManager(new InitialContext());
        } catch (SQLException | NamingException e) {
            throw new IllegalArgumentException();
        }
        htmlManager = new HTMLManager(dataManager);
        registerManager = new SimpleRegisterManager(dataManager);
        loginManager = new SimpleLoginManager(dataManager);
        dispatcher = new RequestDispatcher(htmlManager, loginManager, registerManager);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
        response.setCharacterEncoding("UTF-8");
        dispatcher.dispatch(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
        response.setCharacterEncoding("UTF-8");
        dispatcher.dispatch(request, response);
    }
}

import data.DataSource;
import dispatcher.Dispatcher;
import data.SimpleDataSource;
import dispatcher.RequestDispatcher;
import html.HTMLManager;
import html.HtmlGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Main {
    private static String URL = "jdbc:postgresql://localhost:5432/social";
    private static String USER = "postgres";
    private static String PASSWORD = "sdfsdf";

    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        DataSource dataSource;
        try {
            dataSource = new SimpleDataSource(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        HtmlGenerator htmlManager = new HTMLManager(dataSource);
        while (true) {
            String request;
            try {
                request = input.readLine();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                continue;
            }

            Dispatcher dispatcher = new RequestDispatcher(htmlManager);

            htmlManager.show();
        }
    }
}

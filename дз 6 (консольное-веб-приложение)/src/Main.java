import data.DataSource;
import html.HTMLManager;
import html.Page;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Main {
    private static String URL = "jdbc:postgresql://localhost:5432/social";
    private static String USER = "postgres";
    private static String PASSWORD = "sdfsdf";

    public static void main(String[] args) {
        HTMLManager htmlManager = new HTMLManager();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        DataSource dataSource;
        try {
            dataSource = new DataSource(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        while (true) {
            String request;
            try {
                request = input.readLine();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                continue;
            }

            String[] req = request.split("/");
            Page page;
            try {
                page = Page.valueOf(req[1]);
            } catch (IllegalArgumentException e) {
                htmlManager.generate(Page.notFound, dataSource);
                htmlManager.show(Page.notFound);
                continue;
            }

            if (page == Page.id) {
                if (req.length < 3) {
                    htmlManager.generate(Page.notFound, dataSource);
                } else {
                    try {
                        htmlManager.generate(Page.id, Integer.parseInt(req[2]), dataSource);
                    } catch (NumberFormatException e) {
                        htmlManager.generate(Page.notFound, dataSource);
                    }
                }
            } else {
                htmlManager.generate(page, dataSource);
            }
            htmlManager.show(page);
        }
    }
}

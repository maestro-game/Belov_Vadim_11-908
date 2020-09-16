import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    static final String url = "https://kartaslov.ru/синонимы-к-слову/";
    static final String USER_AGENT = " Mozilla/5.0";

    public static void main(String[] args) throws IOException {
        System.out.println("Подбор синонимов к слову: ");
        HttpURLConnection connection = (HttpURLConnection) new URL(url + (new Scanner(System.in)).next()).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String req = reader.lines().skip(5).findFirst().orElse("");
        System.out.println(req.substring(req.indexOf(':') + 1, req.indexOf('.')));
    }
}
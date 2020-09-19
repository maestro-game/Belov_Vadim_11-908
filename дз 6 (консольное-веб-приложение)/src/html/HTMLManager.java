package html;

import data.DataSource;
import models.Message;
import models.Post;
import models.User;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class HTMLManager {
    public static final String PATH = ".temp.html";
    private static FileWriter writer;
    private static File file;

    static {
        file = new File(PATH);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void generateFeed(List<Post> posts) throws IOException {
        writer.write("<!DOCTYPE html>\n" +
                "<html lang=\"en-US\">\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Кормить</title>\n" +
                "</head>\n" +
                "\t<body>\n");
        posts.forEach(post -> {
            try {
                writer.write("\t\t<div>\n" +
                        "<h2 style=\"text-align: center\">" + post.getName() + "</h2>" +
                        "<h3>" + post.getAuthor().getName() + post.getAuthor().getSurname() + "</h3>" +
                        "<h4>" + post.getDate().toString() + "</h4>" +
                        "<p>" + post.getText() + "</p>" +
                        "\t\t</div>\n");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
        writer.write("\t</body>\n" +
                "</html>");
        writer.close();
    }

    private static void generateMessages(List<Message> messages) throws IOException {
        writer.write("<!DOCTYPE html>\n" +
                "<html lang=\"en-US\">\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Флудильня</title>\n" +
                "</head>\n" +
                "\t<body>\n");
        messages.forEach(message -> {
            try {
                writer.write("\t\t<div>\n" +
                        "<h2>" + message.getFrom().getName() + " " + message.getFrom().getSurname() + " => " + message.getTo().getName() + " " + message.getTo().getSurname() + "</h2>" +
                        "<h4>" + message.getDate().toString() + "</h4>" +
                        "<p>" + message.getText() + "</p>" +
                        "\t\t</div>\n");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
        writer.write("\t</body>\n" +
                "</html>");
        writer.close();
    }

    private static void generateId(User user) throws IOException {
        if (user == null) {
            generateNotFound();
            return;
        }
        writer.write("<!DOCTYPE html>\n" +
                "<html lang=\"en-US\">\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>" + user.getName() + " " + user.getSurname() + "</title>\n" +
                "</head>\n" +
                "\t<body>\n" +
                "<h2>" + user.getName() + " " + user.getSurname() + "</h2>" +
                "<h4>" + "Возраст: " + user.getAge() + ", " + user.getGroup() + ", " + user.getCourse() + " курс" + "</h4>" +
                "\t</body>\n" +
                "</html>");
        writer.close();
    }

    private static void generateNotFound() throws IOException {
        writer.write("<!DOCTYPE html>\n" +
                "<html lang=\"en-US\">\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Как так?</title>\n" +
                "</head>\n" +
                "\t<body style=\"height: auto; margin: 0\">\n" +
                "\t<div style=\"background: #f8f5f4; display: flex; height: 100vmin\">\n" +
                "\t\t<div style=\"margin: auto\">\n" +
                "\t\t\t<img src=\"https://www.minecraft.net/content/dam/minecraft/creeper.png\">\n" +
                "\t\t\t<h2>404: Not Found</h2>\n" +
                "\t\t</div>\n" +
                "\t</div>\n" +
                "\t</body>\n" +
                "</html>");
        writer.close();
    }

    public static void generate(Page page, DataSource dataSource) {
        generate(page, 0, dataSource);
    }

    public static void generate(Page page, int param, DataSource dataSource) {
        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            switch (page) {
                case feed: {
                    generateFeed(dataSource.getPosts());
                    break;
                }
                case messages: {
                    generateMessages(dataSource.getMessages());
                    break;
                }
                case notFound: {
                    generateNotFound();
                    break;
                }
                case id: {
                    generateId(dataSource.getUser(param));
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void show(Page page) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(PATH));
            } catch (URISyntaxException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

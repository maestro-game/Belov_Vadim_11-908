package models;

import java.sql.Date;
import java.sql.Timestamp;

public class Post {
    int id;
    String name;
    User author;
    Timestamp date;
    String text;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public User getAuthor() {
        return author;
    }

    public Post(int id, String name, User author, Timestamp date, String text) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.date = date;
        this.text = text;
    }
}

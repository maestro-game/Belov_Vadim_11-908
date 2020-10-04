package models;

import java.sql.Timestamp;

public class Post {
    int id;
    User author;
    Timestamp timestamp;
    String text;

    public int getId() {
        return id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getText() {
        return text;
    }

    public User getAuthor() {
        return author;
    }

    public Post(int id, User author, Timestamp timestamp, String text) {
        this.id = id;
        this.author = author;
        this.timestamp = timestamp;
        this.text = text;
    }
}

package models;

import java.sql.Timestamp;

public class Message {
    int id;
    User from, to;
    Timestamp timestamp;
    String text;

    public int getId() {
        return id;
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    public Timestamp getDate() {
        return timestamp;
    }

    public String getText() {
        return text;
    }

    public Message(int id, User from, User to, Timestamp timestamp, String text) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.timestamp = timestamp;
        this.text = text;
    }
}

package models;

import java.util.Date;
import java.util.Objects;

public class User {
    String id;
    String name, surname, group;
    byte course;
    Date birth;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGroup() {
        return group;
    }

    public Date getBirth() {
        return birth;
    }

    public byte getCourse() {
        return course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public User(String id, String name, String surname, java.sql.Date birth, byte course, String group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.birth = birth;
        this.course = course;
    }
}

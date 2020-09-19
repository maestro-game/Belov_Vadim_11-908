package models;

import java.util.Objects;

public class User {
    int id;
    String name, surname, group;
    byte age, course;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGroup() {
        return group;
    }

    public byte getAge() {
        return age;
    }

    public byte getCourse() {
        return course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public User(int id, String name, String surname, byte age, byte course, String group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.age = age;
        this.course = course;
    }
}

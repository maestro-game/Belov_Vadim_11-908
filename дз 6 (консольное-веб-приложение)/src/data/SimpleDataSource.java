package data;

import models.Message;
import models.Post;
import models.User;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SimpleDataSource implements DataSource{
    private static final String GET_MESSAGES = "select * from messages order by date desc limit 30";
    private static final String GET_POSTS = "select * from posts order by date desc limit 15";
    private static final String GET_USER = "select * from users where id=";

    Connection connection;

    public List<Message> getMessages() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_MESSAGES);
            LinkedList<Message> result = new LinkedList<>();
            HashMap<Integer, User> map = new HashMap<>(100);
            while (resultSet.next()) {
                User from;
                int fromId = resultSet.getInt(2);
                int toId = resultSet.getInt(3);
                if (map.containsKey(fromId)) {
                    from = map.get(fromId);
                } else {
                    from = getUser(fromId);
                    map.put(fromId, from);
                }
                User to;
                if (map.containsKey(toId)) {
                    to = map.get(toId);
                } else {
                    to = getUser(toId);
                    map.put(toId, to);
                }
                result.add(new Message(resultSet.getInt(1), from, to, resultSet.getTimestamp(4), resultSet.getString(5)));
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Post> getPosts() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_POSTS);
            LinkedList<Post> result = new LinkedList<>();
            HashMap<Integer, User> map = new HashMap<>(30);
            while (resultSet.next()) {
                User user;
                int userId = resultSet.getInt(3);
                if (map.containsKey(userId)) {
                    user = map.get(userId);
                } else {
                    user = getUser(userId);
                    map.put(userId, user);
                }
                result.add(new Post(resultSet.getInt(1), resultSet.getString(2), user, resultSet.getTimestamp(4), resultSet.getString(5)));
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public User getUser(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_USER + id);
            if (resultSet.next()) {
                return new User(id, resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4), resultSet.getByte(5), resultSet.getString(6));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public SimpleDataSource(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }
}

package data;

import models.Message;
import models.Post;
import models.User;

import javax.naming.Context;
import javax.naming.NamingException;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SimpleDataManager implements DataManager {

    private static final String GET_MESSAGES = "select * from messages order by timestamp desc limit 30";
    private static final String GET_USER = "select * from users where id=";
    //language=PostgreSQL
    private static final String GET_POSTS = "select * from posts where author=%s order by timestamp desc limit 10";
    //language=PostgreSQL
    private static final String ADD_USER = "insert into users values('%s', '%s', '%s', '%s', '%d', '%s', md5('he he boiii%s'))";


    Connection connection;

    @Override
    public List<Message> getMessages() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_MESSAGES);
            LinkedList<Message> result = new LinkedList<>();
            HashMap<String, User> map = new HashMap<>(100);
            while (resultSet.next()) {
                User from;
                String fromId = resultSet.getString(2);
                String toId = resultSet.getString(3);
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

    @Override
    public List<Post> getPosts(String id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_POSTS.formatted(id));
            LinkedList<Post> result = new LinkedList<>();
            HashMap<String, User> map = new HashMap<>(20);
            while (resultSet.next()) {
                User user;
                String userId = resultSet.getString(2);
                if (map.containsKey(userId)) {
                    user = map.get(userId);
                } else {
                    user = getUser(userId);
                    map.put(userId, user);
                }
                result.add(new Post(resultSet.getInt(1), user, resultSet.getTimestamp(3), resultSet.getString(4)));
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public User getUser(String id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_USER + id);
            if (resultSet.next()) {
                return new User(id, resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), resultSet.getByte(5), resultSet.getString(6));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean addUser(User user, String password) {
        try {
            Statement statement = connection.createStatement();
            return statement.execute(ADD_USER.formatted(user.getId(), user.getName(), user.getSurname(), user.getBirth().toString(), user.getCourse(), user.getGroup(), password));
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                return false;
            } else {
                throw new IllegalArgumentException(e);
            }
        }
    }

    public SimpleDataManager(Context context) throws SQLException {
        try {
            Context envContext = (Context) context.lookup("java:comp/env");
            connection = ((javax.sql.DataSource) envContext.lookup("jdbc/social")).getConnection();
        } catch (NamingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

package data;

import models.Message;
import models.Post;
import models.User;

import java.util.List;

public interface DataManager {

    List<Message> getMessages();

    List<Post> getPosts(String id);

    User getUser(String id);

    int addUser(User user, String password);
}

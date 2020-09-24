package data;

import models.Message;
import models.Post;
import models.User;

import java.util.List;

public interface DataSource {

    public List<Message> getMessages();

    public List<Post> getPosts();

    public User getUser(int id);
}

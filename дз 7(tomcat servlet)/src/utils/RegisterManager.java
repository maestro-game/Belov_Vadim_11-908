package utils;

import models.User;

public interface RegisterManager {
    boolean register(User user, String password);
}

package utils;

import models.User;

public interface RegisterManager {
    int register(User user, String password);
}

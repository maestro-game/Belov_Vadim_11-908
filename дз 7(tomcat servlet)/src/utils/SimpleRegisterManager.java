package utils;

import data.DataManager;
import models.User;

public class SimpleRegisterManager implements RegisterManager {
    private DataManager dataSource;

    @Override
    public int register(User user, String password) {
        return dataSource.addUser(user, password);
    }

    public SimpleRegisterManager(DataManager dataSource) {
        this.dataSource = dataSource;
    }
}

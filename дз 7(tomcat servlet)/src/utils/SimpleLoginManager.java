package utils;

import data.DataManager;

public class SimpleLoginManager implements LoginManager {
    private DataManager dataManager;

    public SimpleLoginManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}

package com.expert_soft.prihodko.task.dao.connection;

import java.util.ResourceBundle;

public class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();
    private final static String resourcesDB = "com.expert_soft.prihodko.task/resources/database";
    private final static ResourceBundle bundle = ResourceBundle.getBundle(resourcesDB);
    public static DBResourceManager getInstance() {
        return instance;
    }
    public String getValue(String key){
        return bundle.getString(key);
    }

}

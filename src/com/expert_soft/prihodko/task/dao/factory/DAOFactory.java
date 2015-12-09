package com.expert_soft.prihodko.task.dao.factory;
import com.expert_soft.prihodko.task.dao.entity.*;

public abstract class DAOFactory {
    private final static MySQLDAOFactory mySqlDAOFactory = new MySQLDAOFactory();
    /**Enum of DAO types supported by the factory*/
    public static enum DataSourceName{
        MYSQL
    }
    /**
     * There are methods for each DAO that can be created.
     * The concrete factories implement these methods
     * */
    public abstract ContactDao getContactDao();
    /**
     * Method that gets needed  DAOFactory
     * */
    public static DAOFactory getDAOFactory(DataSourceName dataSourceName ){
        switch (dataSourceName){
            case MYSQL:
                return mySqlDAOFactory;
            default:
                return null;
        }
    }
}

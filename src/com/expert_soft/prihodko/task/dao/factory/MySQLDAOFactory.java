package com.expert_soft.prihodko.task.dao.factory;

import com.expert_soft.prihodko.task.dao.entity.*;
import com.expert_soft.prihodko.task.dao.impl.*;

/**
 * Concrete factory
 * */
public class MySQLDAOFactory extends DAOFactory {

    public ContactDao getContactDao(){
        return new MySQLContactDao();
    }
}

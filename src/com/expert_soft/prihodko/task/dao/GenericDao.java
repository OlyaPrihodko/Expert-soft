package com.expert_soft.prihodko.task.dao;
import com.expert_soft.prihodko.task.exception.daoException.DaoException;

import java.util.ArrayList;
import java.util.Collection;

public interface GenericDao <T>{
    // public T getById(int domainId) throws DaoException;
    public void insert(ArrayList<T> contactArrayList)  throws DaoException;
    /*public void update(T domain) throws DaoException;
    public void delete(T domain) throws DaoException;
    */
    public Collection<T> getAll() throws DaoException;

}

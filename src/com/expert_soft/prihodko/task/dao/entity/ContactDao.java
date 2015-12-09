package com.expert_soft.prihodko.task.dao.entity;


import com.expert_soft.prihodko.task.dao.GenericDao;
import com.expert_soft.prihodko.task.entity.Contact;
import com.expert_soft.prihodko.task.exception.daoException.DaoException;

import java.util.ArrayList;
import java.util.Collection;

public interface ContactDao extends GenericDao<Contact> {
    //public Contact getById(int id) throws DaoException;
    public void insert(ArrayList<Contact> contactArrayList)throws DaoException;
    /*public void create(Contact contact)throws DaoException;
    public void update(Contact contact) throws DaoException;
    public void delete(Contact contact)throws DaoException;*/
    public Collection<Contact> getAll() throws DaoException;
}
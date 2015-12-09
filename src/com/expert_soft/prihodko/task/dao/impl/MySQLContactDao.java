package com.expert_soft.prihodko.task.dao.impl;


import com.expert_soft.prihodko.task.controller.listener.ContextServletListener;
import com.expert_soft.prihodko.task.dao.DataBaseParameterName;
import com.expert_soft.prihodko.task.dao.connection.ConnectionPool;
import com.expert_soft.prihodko.task.dao.entity.ContactDao;
import com.expert_soft.prihodko.task.entity.Contact;
import com.expert_soft.prihodko.task.exception.daoException.ConnectionPoolException;
import com.expert_soft.prihodko.task.exception.daoException.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MySQLContactDao implements ContactDao {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final static String getAllContacts = "select * from contact";
    private final static String check ="select count(*) from database.contact where (name=? and surname=? and login=? and  "+
            "email=? and phone=?)";
    private final static String insertContact = "insert into contact (name, surname, login, email, phone) values (?,?,?,?,?)";
    public void insert (ArrayList<Contact> contactArrayList)throws DaoException{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try{
            connection =  ContextServletListener.connectionPool.takeConnection();
            connection.setAutoCommit(false);
            for(Contact contact:contactArrayList){
                preparedStatement = connection.prepareStatement(check);
                preparedStatement.setString(1,contact.getName());
                preparedStatement.setString(2,contact.getSurname());
                preparedStatement.setString(3,contact.getLogin());
                preparedStatement.setString(4,contact.getEmail());
                preparedStatement.setString(5,contact.getPhone());
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    int r = resultSet.getInt(1);
                    if(r==0){
                        preparedStatement = connection.prepareStatement(insertContact);
                        preparedStatement.setString(1,contact.getName());
                        preparedStatement.setString(2,contact.getSurname());
                        preparedStatement.setString(3,contact.getLogin());
                        preparedStatement.setString(4,contact.getEmail());
                        preparedStatement.setString(5,contact.getPhone());
                        preparedStatement.execute();
                    }
                }
            }
            connection.commit();
        }catch (SQLException e){
            throw new DaoException("MySQLContactDao has problem with Sql in insert method",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLContactDao has problem with connection pool in insert method",e);
        }
        finally {
            ContextServletListener.connectionPool.closeConnection(connection, preparedStatement);
        }

    }
    /*public Contact getById(int id)throws DaoException {
        return null;
    }
    public void create (Contact contact)throws DaoException{

    }
    public void update(Contact contact)throws DaoException{

    }
    public void delete(Contact contact)throws DaoException{

    }*/
    public Collection<Contact> getAll() throws DaoException{
        Set<Contact> contactSet = new HashSet<Contact>();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet =null;
        try{
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(getAllContacts);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString(DataBaseParameterName.NAME);
                String surname = resultSet.getString(DataBaseParameterName.SURNAME);
                String login = resultSet.getString(DataBaseParameterName.LOGIN);
                String email = resultSet.getString(DataBaseParameterName.EMAIL);
                String phone = resultSet.getString(DataBaseParameterName.PHONE);
                Contact contact = new Contact(name,surname,login,email,phone);
                contactSet.add(contact);
            }

        }catch (SQLException e){
            throw new DaoException("MySQLContactDao has problem with Sql in getAll method",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLContactDao has problem with connection pool in getAll method",e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement,resultSet);
        }
        return contactSet;
    }
}

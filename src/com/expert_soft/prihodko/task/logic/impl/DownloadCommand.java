package com.expert_soft.prihodko.task.logic.impl;

import com.expert_soft.prihodko.task.controller.RequestParameterName;
import com.expert_soft.prihodko.task.dao.entity.ContactDao;
import com.expert_soft.prihodko.task.dao.factory.DAOFactory;
import com.expert_soft.prihodko.task.entity.Contact;
import com.expert_soft.prihodko.task.exception.daoException.DaoException;
import com.expert_soft.prihodko.task.exception.logicException.LogicException;
import com.expert_soft.prihodko.task.logic.FileOperations;
import com.expert_soft.prihodko.task.logic.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class DownloadCommand implements ICommand {
    private final static DAOFactory mySQLDaoFactory = DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
    private final static ContactDao contactDao = mySQLDaoFactory.getContactDao();
    @Override
    public String execute(HttpServletRequest request)  throws LogicException {
        String page = (String)request.getSession().getAttribute(RequestParameterName.CURRENT_PAGE);
        Object ob = request.getSession().getAttribute(RequestParameterName.LANGUAGE);
        ResourceBundle resourceBundle;
        if(!(ob instanceof Locale)){
            String lan = (String)ob;
            String language = lan.substring(0,2);
            String country = lan.substring(3,5);
            Locale locale = new Locale(language,country);
            resourceBundle =ResourceBundle.getBundle(RequestParameterName.LOCALE,locale);
        }
        else{
            resourceBundle = ResourceBundle.getBundle(RequestParameterName.LOCALE, (Locale) ob);
        }
        FileOperations fileOperations = new FileOperations();
        String stringAllData = fileOperations.getFileInfo(request);
        if(stringAllData.isEmpty()){
            request.setAttribute(RequestParameterName.FILE_INFO, resourceBundle.getString("locale.message.EmptyFile"));
        }
        else{
            try{
                ArrayList<Contact> contacts = fileOperations.parseStringData(stringAllData);
                if(!fileOperations.isSomethingIncorrectData()){
                    contactDao.insert(contacts);
                    request.setAttribute(RequestParameterName.FILE_INFO, resourceBundle.getString("locale.message.IncorrectData"));
                }else{

                    contactDao.insert(contacts);
                    request.setAttribute(RequestParameterName.FILE_INFO, resourceBundle.getString("locale.message.SuccessfulImport"));

                }
            }catch (DaoException e){
                throw new LogicException("ContactDao has problem in insert method", e);
            }
        }
        return page;
    }
}

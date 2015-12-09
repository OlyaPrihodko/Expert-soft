package com.expert_soft.prihodko.task.logic.impl;

import com.expert_soft.prihodko.task.controller.CommandName;
import com.expert_soft.prihodko.task.controller.JSPPageName;
import com.expert_soft.prihodko.task.controller.RequestParameterName;
import com.expert_soft.prihodko.task.dao.entity.ContactDao;
import com.expert_soft.prihodko.task.dao.factory.DAOFactory;
import com.expert_soft.prihodko.task.entity.Contact;
import com.expert_soft.prihodko.task.exception.daoException.DaoException;
import com.expert_soft.prihodko.task.exception.logicException.LogicException;
import com.expert_soft.prihodko.task.logic.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * GoToPageCommand returns the right page and executes the necessary operations if necessary
 * */
public class GoToPageCommand implements ICommand{
    private static DAOFactory mySQLDaoFactory = DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
    private static ContactDao contactDao = mySQLDaoFactory.getContactDao();
    @Override
    public String execute(HttpServletRequest request)  throws LogicException {
        CommandName commandName = CommandName.valueOf(request.getParameter(RequestParameterName.COMMAND_NAME).toUpperCase().replace("-","_"));
        switch (commandName){
            case GO_TO_IMPORT_PAGE:
                return JSPPageName.IMPORT_PAGE;

            case PREVIOUS_PAGE:
                String str = (String)request.getSession().getAttribute(RequestParameterName.PREVIOUS_PAGE);
                if(str.equals(JSPPageName.MAIN_PAGE)){
                    return JSPPageName.MAIN_PAGE;
                }
            case GO_TO_MAIN_PAGE:
                return JSPPageName.MAIN_PAGE;
            case GO_TO_RULES_PAGE:
                return JSPPageName.RULES_PAGE;
            case GO_TO_CONTACTS_PAGE:
                try {
                    Collection<Contact> contactSet = contactDao.getAll();
                    request.getSession().setAttribute(RequestParameterName.CONTACTS, contactSet);
                } catch (DaoException e) {
                    throw new LogicException("GoToPageCommand has problem with dao",e);
                }
                return JSPPageName.CONTACTS_PAGE;
        }
        return null;
    }
}

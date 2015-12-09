package com.expert_soft.prihodko.task.logic.impl;

import com.expert_soft.prihodko.task.controller.RequestParameterName;
import com.expert_soft.prihodko.task.exception.logicException.LogicException;
import com.expert_soft.prihodko.task.logic.ICommand;

import javax.servlet.http.HttpServletRequest;

public class ChangeLanguageCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request)  throws LogicException {
        String page = (String)request.getSession().getAttribute(RequestParameterName.CURRENT_PAGE);
        return page;
    }
}

package com.expert_soft.prihodko.task.controller;


import com.expert_soft.prihodko.task.exception.controllerException.ControllerException;
import com.expert_soft.prihodko.task.logic.ICommand;
import com.expert_soft.prihodko.task.logic.impl.ChangeLanguageCommand;
import com.expert_soft.prihodko.task.logic.impl.DownloadCommand;
import com.expert_soft.prihodko.task.logic.impl.GoToPageCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandHelper {
    private Map<CommandName,ICommand> mapCommand = new HashMap<CommandName,ICommand>();
    public CommandHelper(){
        ICommand goToPageCommand = new GoToPageCommand();
        mapCommand.put(CommandName.GO_TO_IMPORT_PAGE,goToPageCommand);
        mapCommand.put(CommandName.GO_TO_MAIN_PAGE,goToPageCommand);
        mapCommand.put(CommandName.GO_TO_CONTACTS_PAGE,goToPageCommand);
        mapCommand.put(CommandName.GO_TO_RULES_PAGE,goToPageCommand);
        mapCommand.put(CommandName.PREVIOUS_PAGE,goToPageCommand);
        mapCommand.put(CommandName.CHANGE_LANGUAGE,new ChangeLanguageCommand());
        mapCommand.put(CommandName.DOWNLOAD,new DownloadCommand());
    }
    public ICommand defineCommand(HttpServletRequest request)throws ControllerException{
        String nameCommand;
        try{
            nameCommand  = request.getParameter(RequestParameterName.COMMAND_NAME).toUpperCase().replace("-","_");
        }
        catch (NullPointerException e){
            nameCommand = CommandName.DOWNLOAD.toString();
        }
        CommandName commandName = CommandName.valueOf(nameCommand);
        ICommand iCommand = mapCommand.get(commandName);
        return iCommand;
    }
}

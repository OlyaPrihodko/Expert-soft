package com.expert_soft.prihodko.task.exception.controllerException;

public class ControllerException extends Exception {
    private static final long serialVersionUID = 1L;
    private Exception cause;
    private String message;

    public ControllerException(String msg, Exception e){
        super(msg,e);
        message = msg;
        cause = e;
    }
    @Override
    public synchronized Exception getCause(){
        return (cause==this ? null : cause);
    }
    @Override
    public String getMessage(){
        return message;
    }
}

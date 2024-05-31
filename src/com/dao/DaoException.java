package com.dao;

public class DaoException extends Exception{
    private static final long serialCersionUID = 19192L;
    private String message;

    public DaoException(String message1) {
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DaoException() {
    }
    public String toString(){
        return message;
    }

}

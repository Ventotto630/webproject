package com.model;

public class Customer {
    private String username;//µÇÂ¼Ãû
    private String password;//ÃÜÂë
    public Customer(){}
    public Customer(String username,String password){
        this.username = username;
        this.password = password;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
}

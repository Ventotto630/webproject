package com.model;

public class Administrators {
    int adminID;//����Ա���
    String name;//����
    String username;//��¼��
    String password;//����
    int departmentID;//���ڲ��ű��
    String phonenumber;//��ϵ�绰

    public Administrators(){}
    public Administrators(int adminID, String name, String username, String password, int departmentID, String phonenumber) {
        this.adminID = adminID;
        this.name = name;
        this.username = username;
        this.password = password;
        this.departmentID = departmentID;
        this.phonenumber = phonenumber;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
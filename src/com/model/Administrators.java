package com.model;

import java.io.Serializable;

public class Administrators implements Serializable {
    String adminID;//管理员编号
    String name;//姓名
    String username;//登录名
    String password;//密码
    String departmentID;//所在部门编号
    String phone;//联系电话
    String role;//管理员种类

    public Administrators(){}

    public Administrators(String adminID, String name, String username, String password, String departmentID, String phone, String role) {
        this.adminID = adminID;
        this.name = name;
        this.username = username;
        this.password = password;
        this.departmentID = departmentID;
        this.phone = phone;
        this.role = role;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
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

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

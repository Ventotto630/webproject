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
//    部门管理员对社会预约和（除本部门外其他部门）公务预约的权限，有权限为1
    String social;
    String pub;

    public Administrators(){}

    public Administrators(String adminID, String name, String username, String password, String departmentID, String phone, String role, String social, String pub) {
        this.adminID = adminID;
        this.name = name;
        this.username = username;
        this.password = password;
        this.departmentID = departmentID;
        this.phone = phone;
        this.role = role;
        this.social = social;
        this.pub = pub;
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

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }
}

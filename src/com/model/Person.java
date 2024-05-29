package com.model;

public class Person {
    private String name;//姓名
    private String perid;//身份证号
    private String phoneNumber;//手机号码

    public Person() {
    }

    public Person(String name, String perid, String phoneNumber) {
        this.name = name;
        this.perid = perid;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerid() {
        return perid;
    }

    public void setPerid(String perid) {
        this.perid = perid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

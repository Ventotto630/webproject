package com.model;

public class Reservation extends Person{
    private String applytime;//申请时间
    private String campus;//校区名字
    private String intime;//入校时间
    private String outtime;//出校时间
    private String unit;//所在单位

    private String vehicle;//交通方式
    private String vname;//车牌号
    private Person friend;//同行者

    public Reservation(){
    }

    public Reservation(String name, String perid, String phoneNumber, String applytime, String campus, String intime, String outtime, String unit, String vehicle, String vname, Person friend) {
        super(name, perid, phoneNumber);
        this.applytime = applytime;
        this.campus = campus;
        this.intime = intime;
        this.outtime = outtime;
        this.unit = unit;
        this.vehicle = vehicle;
        this.vname = vname;
        this.friend = friend;
    }

    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime;
    }

    //车牌号可以不填
    public Reservation(String campus, String intime,String outtime, String unit, String vehicle , Person friend) {
        this.campus = campus;
        this.intime = intime;
        this.outtime = outtime;
        this.unit = unit;
        this.vehicle=vehicle;
        this.friend = friend;
    }


    //随行人员可以不填
    public Reservation(String campus, String intime,String outtime, String unit, String vehicle, String vname) {
        this.campus = campus;
        this.intime = intime;
        this.outtime = outtime;
        this.unit = unit;
        this.vehicle = vehicle;
        this.vname = vname;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public Person getFriend() {
        return friend;
    }

    public void setFriend(Person friend) {
        this.friend = friend;
    }
}

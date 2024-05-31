package com.model;

public class Reservation_public extends Person{
    private String applytime;//申请日期
    private String campus;//校区名字
    private String intime;//入校时间

    private String outtime;//出校时间
    private String unit;//所在单位

    private String vehicle;//交通方式
    private String vname;//车牌号
    private Person friend;//同行者
    private String visitunit;//公务访问部门
    private String receptionist;//公务访问接待人
    private String reason;//来访事由
    private String status;//审核状态
    public Reservation_public() {
    }
    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Reservation_public(String name, String perid, String phoneNumber, String applytime, String campus, String intime, String outtime, String unit, String vehicle, String vname, Person friend, String visitunit, String receptionist, String reason, String status) {
        super(name, perid, phoneNumber);
        this.applytime = applytime;
        this.campus = campus;
        this.intime = intime;
        this.outtime = outtime;
        this.unit = unit;
        this.vehicle = vehicle;
        this.vname = vname;
        this.friend = friend;
        this.visitunit = visitunit;
        this.receptionist = receptionist;
        this.reason = reason;
        this.status = status;
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

    public String getVisitunit() {
        return visitunit;
    }

    public void setVisitunit(String visitunit) {
        this.visitunit = visitunit;
    }

    public String getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(String receptionist) {
        this.receptionist = receptionist;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

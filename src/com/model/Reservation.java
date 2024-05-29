package com.model;

public class Reservation extends Person{
    private String campus;//У������
    private String intime;//��Уʱ��
    private String outtime;//��Уʱ��
    private String unit;//���ڵ�λ

    private String vehicle;//��ͨ��ʽ
    private String vname;//���ƺ�
    private Person friend;//ͬ����

    public Reservation(){
        super();
    }
    public Reservation(String campus, String intime,String outtime, String unit, String vehicle, String vname, Person friend, String name, String id, String phnumber) {
        super(name, id, phnumber);
        this.campus = campus;
        this.intime = intime;
        this.outtime = outtime;
        this.unit = unit;
        this.vehicle = vehicle;
        this.vname = vname;
        this.friend = friend;
    }

    //���ƺſ��Բ���
    public Reservation(String campus, String intime,String outtime, String unit, String vehicle , Person friend) {
        this.campus = campus;
        this.intime = intime;
        this.outtime = outtime;
        this.unit = unit;
        this.vehicle=vehicle;
        this.friend = friend;
    }


    //������Ա���Բ���
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

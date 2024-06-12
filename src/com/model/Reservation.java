package com.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Reservation extends Person implements Serializable {
    //��ס���и���ĳ�Ա������ΪԤԼ����Ϣ��name perid phoneNumber
    private String serid; //��¼��
    private String applytime;//����ʱ��
    private String campus;//У������
    private String intime;//��Уʱ��
    private String outtime;//��Уʱ��
    private String unit;//���ڵ�λ

    private String vehicle;//��ͨ��ʽ
    private String vname;//���ƺ�
    private String Fri_number;//������Ա����
    private ArrayList<Person> friend;//ͬ����
    //ͬ���ߵ���Ϣ Fri_name Fri_perid Fri_phoneNumber
    private String qrcode;//��ά���ַ
    public Reservation(){
    }

    public Reservation(String name, String perid, String phoneNumber, String serid, String applytime, String campus, String intime, String outtime, String unit, String vehicle, String vname, String fri_number, ArrayList<Person> friend, String qrcode) {
        super(name, perid, phoneNumber);
        this.serid = serid;
        this.applytime = applytime;
        this.campus = campus;
        this.intime = intime;
        this.outtime = outtime;
        this.unit = unit;
        this.vehicle = vehicle;
        this.vname = vname;
        Fri_number = fri_number;
        this.friend = friend;
        this.qrcode = qrcode;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getSerid() {
        return serid;
    }

    public void setSerid(String serid) {
        this.serid = serid;
    }


    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime;
    }

    //���ƺſ��Բ���



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

    public String getFri_number() {
        return Fri_number;
    }

    public void setFri_number(String fri_number) {
        Fri_number = fri_number;
    }

    public ArrayList<Person> getFriend() {
        return friend;
    }

    public void setFriend(ArrayList<Person> friend) {
        this.friend = friend;
    }
}

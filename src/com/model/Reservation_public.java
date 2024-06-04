package com.model;

import java.io.Serializable;

public class Reservation_public extends Person implements Serializable {
    //��ס���и���ĳ�Ա������ΪԤԼ����Ϣ��name perid phoneNumber
    private String serid; //��¼��
    private String applytime;//��������
    private String campus;//У������
    private String intime;//��Уʱ��

    private String outtime;//��Уʱ��
    private String unit;//���ڵ�λ

    private String vehicle;//��ͨ��ʽ
    private String vname;//���ƺ�
    private Person friend;//ͬ����
    //ͬ���ߵ���Ϣ Fri_name Fri_perid Fri_phoneNumber
    private String visitunit;//������ʲ���
    private String receptionist;//������ʽӴ���
    private String reason;//��������
    private String status;//���״̬
    private String qrcode;//��ά���ַ �������ѯ
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

    public String getSerid() {
        return serid;
    }

    public void setSerid(String serid) {
        this.serid = serid;
    }

    public Reservation_public(String name, String perid, String phoneNumber, String serid, String applytime, String campus, String intime, String outtime, String unit, String vehicle, String vname, Person friend, String visitunit, String receptionist, String reason, String status, String qrcode) {
        super(name, perid, phoneNumber);
        this.serid = serid;
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
        this.qrcode = qrcode;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
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

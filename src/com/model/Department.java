package com.model;

import java.io.Serializable;

public class Department implements Serializable {
    private int denum;//���ű��
    private String detype;//��������
    private String dename;//��������

    public Department() {
    }

    public Department(int denum, String detype, String dename) {
        this.denum = denum;
        this.detype = detype;
        this.dename = dename;
    }

    public int getDenum() {
        return denum;
    }

    public void setDenum(int denum) {
        this.denum = denum;
    }

    public String getDetype() {
        return detype;
    }

    public void setDetype(String detype) {
        this.detype = detype;
    }

    public String getDename() {
        return dename;
    }

    public void setDename(String dename) {
        this.dename = dename;
    }
}

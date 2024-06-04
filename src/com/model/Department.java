package com.model;

import java.io.Serializable;

public class Department implements Serializable {
    private int denum;//部门编号
    private String detype;//部门类型
    private String dename;//部门名称

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

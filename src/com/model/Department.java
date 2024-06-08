package com.model;

import java.io.Serializable;

public class Department implements Serializable {
    private String id;//部门编号
    private String type;//部门类型
    private String name;//部门名称

    public Department() {
    }

    public Department(String id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

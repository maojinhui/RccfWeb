package com.rccf.model.gzh;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Yeji {

    private int id;
    private String name ;
    private int role;
    private String department;
    private String code ;
    private Double target;
    private Double monthyeji;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getTarget() {
        return target;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setTarget(Double target) {
        this.target = target;
    }

    public Double getMonthyeji() {
        return monthyeji;
    }

    public void setMonthyeji(Double monthyeji) {
        this.monthyeji = monthyeji;
    }
}

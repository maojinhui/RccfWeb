package com.rccf.model.gzh;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Accept {
    private int id;
    private String name;
    private  int role;
    private String code;
    private String department;
    private int monthaccept;
    private int monthend;
    private int monthrefuse;
    private int nowaccept_xindai;
    private int nowaccept_diya;
    private int nowaccept_zhiya;
    private int nowaccept_other;


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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getMonthaccept() {
        return monthaccept;
    }

    public void setMonthaccept(int monthaccept) {
        this.monthaccept = monthaccept;
    }

    public int getMonthend() {
        return monthend;
    }

    public void setMonthend(int monthend) {
        this.monthend = monthend;
    }

    public int getMonthrefuse() {
        return monthrefuse;
    }

    public void setMonthrefuse(int monthrefuse) {
        this.monthrefuse = monthrefuse;
    }

    public int getNowaccept_xindai() {
        return nowaccept_xindai;
    }

    public void setNowaccept_xindai(int nowaccept_xindai) {
        this.nowaccept_xindai = nowaccept_xindai;
    }

    public int getNowaccept_diya() {
        return nowaccept_diya;
    }

    public void setNowaccept_diya(int nowaccept_diya) {
        this.nowaccept_diya = nowaccept_diya;
    }

    public int getNowaccept_zhiya() {
        return nowaccept_zhiya;
    }

    public void setNowaccept_zhiya(int nowaccept_zhiya) {
        this.nowaccept_zhiya = nowaccept_zhiya;
    }

    public int getNowaccept_other() {
        return nowaccept_other;
    }

    public void setNowaccept_other(int nowaccept_other) {
        this.nowaccept_other = nowaccept_other;
    }
}

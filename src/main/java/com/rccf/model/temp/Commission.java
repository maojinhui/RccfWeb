package com.rccf.model.temp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Commission {

    @Id
    private Integer id;

    private String name;

    private String code;

    private Integer length;

    private Double yeji;

    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Double getYeji() {
        return yeji;
    }

    public void setYeji(Double yeji) {
        this.yeji = yeji;
    }
}

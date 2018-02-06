package com.rccf.model.gzh.accpet;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AcceptPeroduceSimple {

    private int rowNo;
    private int id;
    private int type;
    private String agency_name;
    private String name;
    private String code;
    private Integer create_person;

    @Id
    public int getRowNo() {
        return rowNo;
    }

    public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAgency_name() {
        return agency_name;
    }

    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
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

    public Integer getCreate_person() {
        return create_person;
    }

    public void setCreate_person(Integer create_person) {
        this.create_person = create_person;
    }
}

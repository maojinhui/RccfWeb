package com.rccf.model.accept;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HouqiData {


    private String name ;
    private Integer acceptcount;
    private Integer endcount;
    private Integer refusecount;
    private Integer removecount;
    private Double actual;

    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAcceptcount() {
        return acceptcount;
    }

    public void setAcceptcount(Integer acceptcount) {
        this.acceptcount = acceptcount;
    }

    public Integer getEndcount() {
        return endcount;
    }

    public void setEndcount(Integer endcount) {
        this.endcount = endcount;
    }

    public Integer getRefusecount() {
        return refusecount;
    }

    public void setRefusecount(Integer refusecount) {
        this.refusecount = refusecount;
    }

    public Integer getRemovecount() {
        return removecount;
    }

    public void setRemovecount(Integer removecount) {
        this.removecount = removecount;
    }

    public Double getActual() {
        return actual;
    }

    public void setActual(Double actual) {
        this.actual = actual;
    }
}

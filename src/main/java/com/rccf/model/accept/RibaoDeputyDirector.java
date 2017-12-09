package com.rccf.model.accept;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RibaoDeputyDirector {

    private String code;
    private String department;

    private String name ;

    private Double monthyeji;

    private Integer monthaccept;

    private Integer monthend;

    private Integer monthrefuse;

    private Double dayyeji;

    private Integer dayaccept;

    private Integer dayend;

    private Integer dayrefuse;

    private Integer pcount;

    private Integer nowaccept;

    private Integer nowaccept_diya;

    private Integer nowaccept_zhiya;

    private Integer nowaccept_xindai;

    @Id
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

    public Double getMonthyeji() {
        return monthyeji;
    }

    public void setMonthyeji(Double monthyeji) {
        this.monthyeji = monthyeji;
    }

    public Integer getMonthaccept() {
        return monthaccept;
    }

    public void setMonthaccept(Integer monthaccept) {
        this.monthaccept = monthaccept;
    }

    public Integer getMonthend() {
        return monthend;
    }

    public void setMonthend(Integer monthend) {
        this.monthend = monthend;
    }

    public Integer getMonthrefuse() {
        return monthrefuse;
    }

    public void setMonthrefuse(Integer monthrefuse) {
        this.monthrefuse = monthrefuse;
    }

    public Double getDayyeji() {
        return dayyeji;
    }

    public void setDayyeji(Double dayyeji) {
        this.dayyeji = dayyeji;
    }

    public Integer getDayaccept() {
        return dayaccept;
    }

    public void setDayaccept(Integer dayaccept) {
        this.dayaccept = dayaccept;
    }

    public Integer getDayend() {
        return dayend;
    }

    public void setDayend(Integer dayend) {
        this.dayend = dayend;
    }

    public Integer getDayrefuse() {
        return dayrefuse;
    }

    public void setDayrefuse(Integer dayrefuse) {
        this.dayrefuse = dayrefuse;
    }

    public Integer getNowaccept() {
        return nowaccept;
    }

    public void setNowaccept(Integer nowaccept) {
        this.nowaccept = nowaccept;
    }

    public Integer getNowaccept_diya() {
        return nowaccept_diya;
    }

    public void setNowaccept_diya(Integer nowaccept_diya) {
        this.nowaccept_diya = nowaccept_diya;
    }

    public Integer getNowaccept_zhiya() {
        return nowaccept_zhiya;
    }

    public void setNowaccept_zhiya(Integer nowaccept_zhiya) {
        this.nowaccept_zhiya = nowaccept_zhiya;
    }

    public Integer getNowaccept_xindai() {
        return nowaccept_xindai;
    }

    public void setNowaccept_xindai(Integer nowaccept_xindai) {
        this.nowaccept_xindai = nowaccept_xindai;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPcount() {
        return pcount;
    }

    public void setPcount(Integer pcount) {
        this.pcount = pcount;
    }
}

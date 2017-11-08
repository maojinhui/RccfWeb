package com.rccf.model.temp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerTemPc {

    private String id;
    private String name;
    private String phone;
    private Integer sex;
    private Integer age;
    private String birthplace;
    private Integer companycount;
    private Integer housecount;
    private Integer carcount;
    private Double applyamount;
    private Integer term_year;
    private Integer term_month;
    private Integer term_day;
    private Double fee_percent;
    private String process;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public Integer getCompanycount() {
        return companycount;
    }

    public void setCompanycount(Integer companycount) {
        this.companycount = companycount;
    }

    public Integer getHousecount() {
        return housecount;
    }

    public void setHousecount(Integer housecount) {
        this.housecount = housecount;
    }

    public Integer getCarcount() {
        return carcount;
    }

    public void setCarcount(Integer carcount) {
        this.carcount = carcount;
    }

    public Double getApplyamount() {
        return applyamount;
    }

    public void setApplyamount(Double applyamount) {
        this.applyamount = applyamount;
    }

    public Integer getTerm_year() {
        return term_year;
    }

    public void setTerm_year(Integer term_year) {
        this.term_year = term_year;
    }

    public Integer getTerm_month() {
        return term_month;
    }

    public void setTerm_month(Integer term_month) {
        this.term_month = term_month;
    }

    public Integer getTerm_day() {
        return term_day;
    }

    public void setTerm_day(Integer term_day) {
        this.term_day = term_day;
    }

    public Double getFee_percent() {
        return fee_percent;
    }

    public void setFee_percent(Double fee_percent) {
        this.fee_percent = fee_percent;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }
}

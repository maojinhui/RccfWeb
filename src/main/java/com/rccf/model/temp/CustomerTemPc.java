package com.rccf.model.temp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class CustomerTemPc {

    private String id;
    private String name;
    private String phone;
    private String manager_e;
    private String manager_dd;
    private String manager_d;
    private Timestamp create_time;
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
    private Integer loan_type;
    private Integer level;
    private Timestamp admin_time;




    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManager_e() {
        return manager_e;
    }

    public void setManager_e(String manager_e) {
        this.manager_e = manager_e;
    }

    public String getManager_dd() {
        return manager_dd;
    }

    public void setManager_dd(String manager_dd) {
        this.manager_dd = manager_dd;
    }

    public String getManager_d() {
        return manager_d;
    }

    public void setManager_d(String manager_d) {
        this.manager_d = manager_d;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
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


    public Integer getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(Integer loan_type) {
        this.loan_type = loan_type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Timestamp getAdmin_time() {
        return admin_time;
    }

    public void setAdmin_time(Timestamp admin_time) {
        this.admin_time = admin_time;
    }
}

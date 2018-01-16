package com.rccf.model.temp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class CustomerPc {

    private String id;
    private String name;
    private String phone;
    private String manager_e;
    private String manager_dd;
    private String manager_d;
    private Timestamp create_time;
//    private Integer sex;
//    private Integer age;
//    private String birthplace;
//    private Integer companycount;
//    private Integer housecount;
//    private Integer carcount;
    private Double applyamount;
//    private Integer term_year;
//    private Integer term_month;
//    private Integer term_day;
//    private Double fee_percent;
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

    public Double getApplyamount() {
        return applyamount;
    }

    public void setApplyamount(Double applyamount) {
        this.applyamount = applyamount;
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

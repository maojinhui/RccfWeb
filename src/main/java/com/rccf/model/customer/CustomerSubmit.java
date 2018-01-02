package com.rccf.model.customer;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerSubmit {

    private int id;
    private String customer_id;
    private String customer_name;
    private int submit_saleman;
    private String submit_saleman_name;
    private String houqi_name;
    private String month_day;
    private String hourminute;
    private int state;


    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public int getSubmit_saleman() {
        return submit_saleman;
    }

    public void setSubmit_saleman(int submit_saleman) {
        this.submit_saleman = submit_saleman;
    }

    public String getSubmit_saleman_name() {
        return submit_saleman_name;
    }

    public void setSubmit_saleman_name(String submit_saleman_name) {
        this.submit_saleman_name = submit_saleman_name;
    }

    public String getHouqi_name() {
        return houqi_name;
    }

    public void setHouqi_name(String houqi_name) {
        this.houqi_name = houqi_name;
    }

    public String getMonth_day() {
        return month_day;
    }

    public void setMonth_day(String month_day) {
        this.month_day = month_day;
    }

    public String getHourminute() {
        return hourminute;
    }

    public void setHourminute(String hourminute) {
        this.hourminute = hourminute;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

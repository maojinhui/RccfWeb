package com.rccf.model.produce;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class ProduceFront {

    private Integer id;

    private Integer type ;

    private String agency_name;

    private String code ;

    private String name ;

    private String repayment_type;

    private String loan_people;

    private Integer loan_amount_min;

    private Integer loan_amount_max;

    private Integer loan_term_min;

    private Integer loan_term_max;

    private Double loan_rate_min;

    private Double loan_rate_max;

    private Integer loan_credit_type;

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAgency_name() {
        return agency_name;
    }

    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepayment_type() {
        return repayment_type;
    }

    public void setRepayment_type(String repayment_type) {
        this.repayment_type = repayment_type;
    }

    public String getLoan_people() {
        return loan_people;
    }

    public void setLoan_people(String loan_people) {
        this.loan_people = loan_people;
    }

    public Integer getLoan_amount_min() {
        return loan_amount_min;
    }

    public void setLoan_amount_min(Integer loan_amount_min) {
        this.loan_amount_min = loan_amount_min;
    }

    public Integer getLoan_amount_max() {
        return loan_amount_max;
    }

    public void setLoan_amount_max(Integer loan_amount_max) {
        this.loan_amount_max = loan_amount_max;
    }

    public Integer getLoan_term_min() {
        return loan_term_min;
    }

    public void setLoan_term_min(Integer loan_term_min) {
        this.loan_term_min = loan_term_min;
    }

    public Integer getLoan_term_max() {
        return loan_term_max;
    }

    public void setLoan_term_max(Integer loan_term_max) {
        this.loan_term_max = loan_term_max;
    }

    public Double getLoan_rate_min() {
        return loan_rate_min;
    }

    public void setLoan_rate_min(Double loan_rate_min) {
        this.loan_rate_min = loan_rate_min;
    }

    public Double getLoan_rate_max() {
        return loan_rate_max;
    }

    public void setLoan_rate_max(Double loan_rate_max) {
        this.loan_rate_max = loan_rate_max;
    }

    public Integer getLoan_credit_type() {
        return loan_credit_type;
    }

    public void setLoan_credit_type(Integer loan_credit_type) {
        this.loan_credit_type = loan_credit_type;
    }
}

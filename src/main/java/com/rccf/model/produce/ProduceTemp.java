package com.rccf.model.produce;

public class ProduceTemp {

    private Integer amount_min ;

    private Integer amount_max;

    private Integer term_min ;

    private Integer term_max ;

    private String loan_repayment_type;

    private String loan_people ;

    private String loan_rate_min;

    private String loan_rate_max;

    private Integer loan_time_min;

    private Integer loan_time_max;


    public Integer getAmount_min() {
        return amount_min;
    }

    public void setAmount_min(Integer amount_min) {
        this.amount_min = amount_min;
    }

    public Integer getAmount_max() {
        return amount_max;
    }

    public void setAmount_max(Integer amount_max) {
        this.amount_max = amount_max;
    }

    public Integer getTerm_min() {
        return term_min;
    }

    public void setTerm_min(Integer term_min) {
        this.term_min = term_min;
    }

    public Integer getTerm_max() {
        return term_max;
    }

    public void setTerm_max(Integer term_max) {
        this.term_max = term_max;
    }

    public String getLoan_repayment_type() {
        return loan_repayment_type;
    }

    public void setLoan_repayment_type(String loan_repayment_type) {
        this.loan_repayment_type = loan_repayment_type;
    }

    public String getLoan_people() {
        return loan_people;
    }

    public void setLoan_people(String loan_people) {
        this.loan_people = loan_people;
    }

    public String getLoan_rate_min() {
        return loan_rate_min;
    }

    public void setLoan_rate_min(String loan_rate_min) {
        this.loan_rate_min = loan_rate_min;
    }

    public String getLoan_rate_max() {
        return loan_rate_max;
    }

    public void setLoan_rate_max(String loan_rate_max) {
        this.loan_rate_max = loan_rate_max;
    }

    public Integer getLoan_time_min() {
        return loan_time_min;
    }

    public void setLoan_time_min(Integer loan_time_min) {
        this.loan_time_min = loan_time_min;
    }

    public Integer getLoan_time_max() {
        return loan_time_max;
    }

    public void setLoan_time_max(Integer loan_time_max) {
        this.loan_time_max = loan_time_max;
    }
}

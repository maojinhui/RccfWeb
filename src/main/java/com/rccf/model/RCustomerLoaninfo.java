package com.rccf.model;

import javax.persistence.*;

@Entity
@Table(name = "r_customer_loaninfo", schema = "rccf", catalog = "")
public class RCustomerLoaninfo {
    private int id;
    private String customerId;
    private Double applyLoanAmount;
    private Integer loanTermYear;
    private Integer loanTermMonth;
    private Integer loanTermDay;
    private String loanUsage;
    private Integer loanRepayment;
    private Double loanFeePercent;
    private Integer loanMonthlyRepayment;
    private String loanRepaymentSource;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "customer_id")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "apply_loan_amount")
    public Double getApplyLoanAmount() {
        return applyLoanAmount;
    }

    public void setApplyLoanAmount(Double applyLoanAmount) {
        this.applyLoanAmount = applyLoanAmount;
    }

    @Basic
    @Column(name = "loan_term_year")
    public Integer getLoanTermYear() {
        return loanTermYear;
    }

    public void setLoanTermYear(Integer loanTermYear) {
        this.loanTermYear = loanTermYear;
    }

    @Basic
    @Column(name = "loan_term_month")
    public Integer getLoanTermMonth() {
        return loanTermMonth;
    }

    public void setLoanTermMonth(Integer loanTermMonth) {
        this.loanTermMonth = loanTermMonth;
    }

    @Basic
    @Column(name = "loan_term_day")
    public Integer getLoanTermDay() {
        return loanTermDay;
    }

    public void setLoanTermDay(Integer loanTermDay) {
        this.loanTermDay = loanTermDay;
    }

    @Basic
    @Column(name = "loan_usage")
    public String getLoanUsage() {
        return loanUsage;
    }

    public void setLoanUsage(String loanUsage) {
        this.loanUsage = loanUsage;
    }

    @Basic
    @Column(name = "loan_repayment")
    public Integer getLoanRepayment() {
        return loanRepayment;
    }

    public void setLoanRepayment(Integer loanRepayment) {
        this.loanRepayment = loanRepayment;
    }

    @Basic
    @Column(name = "loan_fee_percent")
    public Double getLoanFeePercent() {
        return loanFeePercent;
    }

    public void setLoanFeePercent(Double loanFeePercent) {
        this.loanFeePercent = loanFeePercent;
    }

    @Basic
    @Column(name = "loan_monthly_repayment")
    public Integer getLoanMonthlyRepayment() {
        return loanMonthlyRepayment;
    }

    public void setLoanMonthlyRepayment(Integer loanMonthlyRepayment) {
        this.loanMonthlyRepayment = loanMonthlyRepayment;
    }

    @Basic
    @Column(name = "loan_repayment_source")
    public String getLoanRepaymentSource() {
        return loanRepaymentSource;
    }

    public void setLoanRepaymentSource(String loanRepaymentSource) {
        this.loanRepaymentSource = loanRepaymentSource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RCustomerLoaninfo loaninfo = (RCustomerLoaninfo) o;

        if (id != loaninfo.id) return false;
        if (customerId != null ? !customerId.equals(loaninfo.customerId) : loaninfo.customerId != null) return false;
        if (applyLoanAmount != null ? !applyLoanAmount.equals(loaninfo.applyLoanAmount) : loaninfo.applyLoanAmount != null)
            return false;
        if (loanTermYear != null ? !loanTermYear.equals(loaninfo.loanTermYear) : loaninfo.loanTermYear != null)
            return false;
        if (loanTermMonth != null ? !loanTermMonth.equals(loaninfo.loanTermMonth) : loaninfo.loanTermMonth != null)
            return false;
        if (loanTermDay != null ? !loanTermDay.equals(loaninfo.loanTermDay) : loaninfo.loanTermDay != null)
            return false;
        if (loanUsage != null ? !loanUsage.equals(loaninfo.loanUsage) : loaninfo.loanUsage != null) return false;
        if (loanRepayment != null ? !loanRepayment.equals(loaninfo.loanRepayment) : loaninfo.loanRepayment != null)
            return false;
        if (loanFeePercent != null ? !loanFeePercent.equals(loaninfo.loanFeePercent) : loaninfo.loanFeePercent != null)
            return false;
        if (loanMonthlyRepayment != null ? !loanMonthlyRepayment.equals(loaninfo.loanMonthlyRepayment) : loaninfo.loanMonthlyRepayment != null)
            return false;
        if (loanRepaymentSource != null ? !loanRepaymentSource.equals(loaninfo.loanRepaymentSource) : loaninfo.loanRepaymentSource != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (applyLoanAmount != null ? applyLoanAmount.hashCode() : 0);
        result = 31 * result + (loanTermYear != null ? loanTermYear.hashCode() : 0);
        result = 31 * result + (loanTermMonth != null ? loanTermMonth.hashCode() : 0);
        result = 31 * result + (loanTermDay != null ? loanTermDay.hashCode() : 0);
        result = 31 * result + (loanUsage != null ? loanUsage.hashCode() : 0);
        result = 31 * result + (loanRepayment != null ? loanRepayment.hashCode() : 0);
        result = 31 * result + (loanFeePercent != null ? loanFeePercent.hashCode() : 0);
        result = 31 * result + (loanMonthlyRepayment != null ? loanMonthlyRepayment.hashCode() : 0);
        result = 31 * result + (loanRepaymentSource != null ? loanRepaymentSource.hashCode() : 0);
        return result;
    }
}

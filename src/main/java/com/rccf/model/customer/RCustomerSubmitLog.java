package com.rccf.model.customer;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "r_customer_submit_log", schema = "rccf", catalog = "")
public class RCustomerSubmitLog {
    private int id;
    private String customerId;
    private String customerName;
    private String customerPhone;
    private Double customerLoanAmount;
    private Integer customerLoanType;
    private Integer customerLoanTermMonth;
    private String customerLoanUsetype;
    private Integer customerLoanRepayment;
    private Integer customerLoanMonthrepay;
    private String customerRepayResource;
    private Integer customerLoanTermDay;
    private String customerFiles;
    private Integer submitSaleman;
    private String submitDeputy;
    private String submitDirector;
    private Integer submitHouqi;
    private Timestamp submitTime;
    private Integer state;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "customer_id", nullable = true, length = 32)
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "customer_name", nullable = true, length = 255)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "customer_phone", nullable = true, length = 255)
    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Basic
    @Column(name = "customer_loan_amount", nullable = true, precision = 0)
    public Double getCustomerLoanAmount() {
        return customerLoanAmount;
    }

    public void setCustomerLoanAmount(Double customerLoanAmount) {
        this.customerLoanAmount = customerLoanAmount;
    }

    @Basic
    @Column(name = "customer_loan_type", nullable = true)
    public Integer getCustomerLoanType() {
        return customerLoanType;
    }

    public void setCustomerLoanType(Integer customerLoanType) {
        this.customerLoanType = customerLoanType;
    }

    @Basic
    @Column(name = "customer_loan_term_month", nullable = true)
    public Integer getCustomerLoanTermMonth() {
        return customerLoanTermMonth;
    }

    public void setCustomerLoanTermMonth(Integer customerLoanTermMonth) {
        this.customerLoanTermMonth = customerLoanTermMonth;
    }

    @Basic
    @Column(name = "customer_loan_usetype", nullable = true, length = 255)
    public String getCustomerLoanUsetype() {
        return customerLoanUsetype;
    }

    public void setCustomerLoanUsetype(String customerLoanUsetype) {
        this.customerLoanUsetype = customerLoanUsetype;
    }

    @Basic
    @Column(name = "customer_loan_repayment", nullable = true)
    public Integer getCustomerLoanRepayment() {
        return customerLoanRepayment;
    }

    public void setCustomerLoanRepayment(Integer customerLoanRepayment) {
        this.customerLoanRepayment = customerLoanRepayment;
    }

    @Basic
    @Column(name = "customer_loan_monthrepay", nullable = true)
    public Integer getCustomerLoanMonthrepay() {
        return customerLoanMonthrepay;
    }

    public void setCustomerLoanMonthrepay(Integer customerLoanMonthrepay) {
        this.customerLoanMonthrepay = customerLoanMonthrepay;
    }

    @Basic
    @Column(name = "customer_repay_resource", nullable = true, length = 255)
    public String getCustomerRepayResource() {
        return customerRepayResource;
    }

    public void setCustomerRepayResource(String customerRepayResource) {
        this.customerRepayResource = customerRepayResource;
    }

    @Basic
    @Column(name = "customer_loan_term_day", nullable = true)
    public Integer getCustomerLoanTermDay() {
        return customerLoanTermDay;
    }

    public void setCustomerLoanTermDay(Integer customerLoanTermDay) {
        this.customerLoanTermDay = customerLoanTermDay;
    }

    @Basic
    @Column(name = "customer_files", nullable = true, length = 255)
    public String getCustomerFiles() {
        return customerFiles;
    }

    public void setCustomerFiles(String customerFiles) {
        this.customerFiles = customerFiles;
    }

    @Basic
    @Column(name = "submit_saleman", nullable = true)
    public Integer getSubmitSaleman() {
        return submitSaleman;
    }

    public void setSubmitSaleman(Integer submitSaleman) {
        this.submitSaleman = submitSaleman;
    }

    @Basic
    @Column(name = "submit_deputy", nullable = true, length = 255)
    public String getSubmitDeputy() {
        return submitDeputy;
    }

    public void setSubmitDeputy(String submitDeputy) {
        this.submitDeputy = submitDeputy;
    }

    @Basic
    @Column(name = "submit_director", nullable = true, length = 255)
    public String getSubmitDirector() {
        return submitDirector;
    }

    public void setSubmitDirector(String submitDirector) {
        this.submitDirector = submitDirector;
    }

    @Basic
    @Column(name = "submit_houqi", nullable = true)
    public Integer getSubmitHouqi() {
        return submitHouqi;
    }

    public void setSubmitHouqi(Integer submitHouqi) {
        this.submitHouqi = submitHouqi;
    }

    @Basic
    @Column(name = "submit_time", nullable = true)
    public Timestamp getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }

    @Basic
    @Column(name = "state", nullable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RCustomerSubmitLog log = (RCustomerSubmitLog) o;
        return id == log.id &&
                Objects.equal(customerId, log.customerId) &&
                Objects.equal(customerName, log.customerName) &&
                Objects.equal(customerPhone, log.customerPhone) &&
                Objects.equal(customerLoanAmount, log.customerLoanAmount) &&
                Objects.equal(customerLoanType, log.customerLoanType) &&
                Objects.equal(customerLoanTermMonth, log.customerLoanTermMonth) &&
                Objects.equal(customerLoanUsetype, log.customerLoanUsetype) &&
                Objects.equal(customerLoanRepayment, log.customerLoanRepayment) &&
                Objects.equal(customerLoanMonthrepay, log.customerLoanMonthrepay) &&
                Objects.equal(customerRepayResource, log.customerRepayResource) &&
                Objects.equal(customerLoanTermDay, log.customerLoanTermDay) &&
                Objects.equal(customerFiles, log.customerFiles) &&
                Objects.equal(submitSaleman, log.submitSaleman) &&
                Objects.equal(submitDeputy, log.submitDeputy) &&
                Objects.equal(submitDirector, log.submitDirector) &&
                Objects.equal(submitHouqi, log.submitHouqi) &&
                Objects.equal(submitTime, log.submitTime) &&
                Objects.equal(state, log.state);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, customerId, customerName, customerPhone, customerLoanAmount, customerLoanType, customerLoanTermMonth, customerLoanUsetype, customerLoanRepayment, customerLoanMonthrepay, customerRepayResource, customerLoanTermDay, customerFiles, submitSaleman, submitDeputy, submitDirector, submitHouqi, submitTime, state);
    }
}

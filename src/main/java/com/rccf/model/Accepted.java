package com.rccf.model;

import com.google.common.base.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Accepted {
    private int id;
    private Timestamp acceptTime;
    private String letterNumber;
    private String acceptedNumber;
    private String customerName;
    private String customerPhone;
    private Integer businessType;
    private String agency;
    private String businessNature;
    private Double wantMoney;
    private Double serviceFee;
    private Double serviceFeeActual;
    private Timestamp endDate;
    private String clerk;
    private Integer state;
    private Double loanMoney;
    private Integer serviceAgreement;
    private String beizhu;
    private Timestamp createTime;
    private String admin;
    private String clerkName;
    private String houqi;
    private String deputyDirector;
    private String director;
    private String process;
    private String agreementNumber;
    private String customerIdcard;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "accept_time")
    public Timestamp getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Timestamp acceptTime) {
        this.acceptTime = acceptTime;
    }

    @Basic
    @Column(name = "letter_number")
    public String getLetterNumber() {
        return letterNumber;
    }

    public void setLetterNumber(String letterNumber) {
        this.letterNumber = letterNumber;
    }

    @Basic
    @Column(name = "accepted_number")
    public String getAcceptedNumber() {
        return acceptedNumber;
    }

    public void setAcceptedNumber(String acceptedNumber) {
        this.acceptedNumber = acceptedNumber;
    }

    @Basic
    @Column(name = "customer_name")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "customer_phone")
    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Basic
    @Column(name = "business_type")
    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    @Basic
    @Column(name = "agency")
    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    @Basic
    @Column(name = "business_nature")
    public String getBusinessNature() {
        return businessNature;
    }

    public void setBusinessNature(String businessNature) {
        this.businessNature = businessNature;
    }

    @Basic
    @Column(name = "want_money")
    public Double getWantMoney() {
        return wantMoney;
    }

    public void setWantMoney(Double wantMoney) {
        this.wantMoney = wantMoney;
    }

    @Basic
    @Column(name = "service_fee")
    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    @Basic
    @Column(name = "service_fee_actual")
    public Double getServiceFeeActual() {
        return serviceFeeActual;
    }

    public void setServiceFeeActual(Double serviceFeeActual) {
        this.serviceFeeActual = serviceFeeActual;
    }

    @Basic
    @Column(name = "end_date")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "clerk")
    public String getClerk() {
        return clerk;
    }

    public void setClerk(String clerk) {
        this.clerk = clerk;
    }

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "loan_money")
    public Double getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(Double loanMoney) {
        this.loanMoney = loanMoney;
    }

    @Basic
    @Column(name = "service_agreement")
    public Integer getServiceAgreement() {
        return serviceAgreement;
    }

    public void setServiceAgreement(Integer serviceAgreement) {
        this.serviceAgreement = serviceAgreement;
    }

    @Basic
    @Column(name = "beizhu")
    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "admin")
    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @Basic
    @Column(name = "clerk_name")
    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    @Basic
    @Column(name = "houqi")
    public String getHouqi() {
        return houqi;
    }

    public void setHouqi(String houqi) {
        this.houqi = houqi;
    }

    @Basic
    @Column(name = "deputy_director")
    public String getDeputyDirector() {
        return deputyDirector;
    }

    public void setDeputyDirector(String deputyDirector) {
        this.deputyDirector = deputyDirector;
    }

    @Basic
    @Column(name = "director")
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Basic
    @Column(name = "process")
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    @Basic
    @Column(name = "agreement_number")
    public String getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    @Basic
    @Column(name = "customer_idcard")
    public String getCustomerIdcard() {
        return customerIdcard;
    }

    public void setCustomerIdcard(String customerIdcard) {
        this.customerIdcard = customerIdcard;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accepted accepted = (Accepted) o;
        return id == accepted.id &&
                Objects.equal(acceptTime, accepted.acceptTime) &&
                Objects.equal(letterNumber, accepted.letterNumber) &&
                Objects.equal(acceptedNumber, accepted.acceptedNumber) &&
                Objects.equal(customerName, accepted.customerName) &&
                Objects.equal(customerPhone, accepted.customerPhone) &&
                Objects.equal(businessType, accepted.businessType) &&
                Objects.equal(agency, accepted.agency) &&
                Objects.equal(businessNature, accepted.businessNature) &&
                Objects.equal(wantMoney, accepted.wantMoney) &&
                Objects.equal(serviceFee, accepted.serviceFee) &&
                Objects.equal(serviceFeeActual, accepted.serviceFeeActual) &&
                Objects.equal(endDate, accepted.endDate) &&
                Objects.equal(clerk, accepted.clerk) &&
                Objects.equal(state, accepted.state) &&
                Objects.equal(loanMoney, accepted.loanMoney) &&
                Objects.equal(serviceAgreement, accepted.serviceAgreement) &&
                Objects.equal(beizhu, accepted.beizhu) &&
                Objects.equal(createTime, accepted.createTime) &&
                Objects.equal(admin, accepted.admin) &&
                Objects.equal(clerkName, accepted.clerkName) &&
                Objects.equal(houqi, accepted.houqi) &&
                Objects.equal(deputyDirector, accepted.deputyDirector) &&
                Objects.equal(director, accepted.director) &&
                Objects.equal(process, accepted.process) &&
                Objects.equal(agreementNumber, accepted.agreementNumber) &&
                Objects.equal(customerIdcard, accepted.customerIdcard);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, acceptTime, letterNumber, acceptedNumber, customerName, customerPhone, businessType, agency, businessNature, wantMoney, serviceFee, serviceFeeActual, endDate, clerk, state, loanMoney, serviceAgreement, beizhu, createTime, admin, clerkName, houqi, deputyDirector, director, process, agreementNumber, customerIdcard);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("acceptTime", acceptTime)
                .add("letterNumber", letterNumber)
                .add("acceptedNumber", acceptedNumber)
                .add("customerName", customerName)
                .add("customerPhone", customerPhone)
                .add("businessType", businessType)
                .add("agency", agency)
                .add("businessNature", businessNature)
                .add("wantMoney", wantMoney)
                .add("serviceFee", serviceFee)
                .add("serviceFeeActual", serviceFeeActual)
                .add("endDate", endDate)
                .add("clerk", clerk)
                .add("state", state)
                .add("loanMoney", loanMoney)
                .add("serviceAgreement", serviceAgreement)
                .add("beizhu", beizhu)
                .add("createTime", createTime)
                .add("admin", admin)
                .add("clerkName", clerkName)
                .add("houqi", houqi)
                .add("deputyDirector", deputyDirector)
                .add("director", director)
                .add("process", process)
                .add("agreementNumber", agreementNumber)
                .add("customerIdcard", customerIdcard)
                .toString();
    }
}

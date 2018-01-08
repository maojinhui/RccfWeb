package com.rccf.model.accept;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "accepted_0105", schema = "rccf", catalog = "")
public class Accepted0105 {
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
    private String clerkName;
    private Integer state;
    private Double loanMoney;
    private Integer serviceAgreement;
    private String beizhu;
    private Timestamp createTime;
    private String admin;
    private String houqi;
    private String deputyDirector;
    private String director;
    private String process;
    private String agreementNumber;
    private String customerIdcard;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "accept_time", nullable = true)
    public Timestamp getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Timestamp acceptTime) {
        this.acceptTime = acceptTime;
    }

    @Basic
    @Column(name = "letter_number", nullable = true, length = 100)
    public String getLetterNumber() {
        return letterNumber;
    }

    public void setLetterNumber(String letterNumber) {
        this.letterNumber = letterNumber;
    }

    @Basic
    @Column(name = "accepted_number", nullable = true, length = 100)
    public String getAcceptedNumber() {
        return acceptedNumber;
    }

    public void setAcceptedNumber(String acceptedNumber) {
        this.acceptedNumber = acceptedNumber;
    }

    @Basic
    @Column(name = "customer_name", nullable = true, length = 225)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "customer_phone", nullable = true, length = 11)
    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Basic
    @Column(name = "business_type", nullable = true)
    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    @Basic
    @Column(name = "agency", nullable = true, length = 225)
    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    @Basic
    @Column(name = "business_nature", nullable = true, length = 225)
    public String getBusinessNature() {
        return businessNature;
    }

    public void setBusinessNature(String businessNature) {
        this.businessNature = businessNature;
    }

    @Basic
    @Column(name = "want_money", nullable = true, precision = 0)
    public Double getWantMoney() {
        return wantMoney;
    }

    public void setWantMoney(Double wantMoney) {
        this.wantMoney = wantMoney;
    }

    @Basic
    @Column(name = "service_fee", nullable = true, precision = 0)
    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    @Basic
    @Column(name = "service_fee_actual", nullable = true, precision = 0)
    public Double getServiceFeeActual() {
        return serviceFeeActual;
    }

    public void setServiceFeeActual(Double serviceFeeActual) {
        this.serviceFeeActual = serviceFeeActual;
    }

    @Basic
    @Column(name = "end_date", nullable = true)
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "clerk", nullable = true, length = 32)
    public String getClerk() {
        return clerk;
    }

    public void setClerk(String clerk) {
        this.clerk = clerk;
    }

    @Basic
    @Column(name = "clerk_name", nullable = true, length = 225)
    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    @Basic
    @Column(name = "state", nullable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "loan_money", nullable = true, precision = 0)
    public Double getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(Double loanMoney) {
        this.loanMoney = loanMoney;
    }

    @Basic
    @Column(name = "service_agreement", nullable = true)
    public Integer getServiceAgreement() {
        return serviceAgreement;
    }

    public void setServiceAgreement(Integer serviceAgreement) {
        this.serviceAgreement = serviceAgreement;
    }

    @Basic
    @Column(name = "beizhu", nullable = true, length = -1)
    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "admin", nullable = true, length = 32)
    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @Basic
    @Column(name = "houqi", nullable = true, length = 32)
    public String getHouqi() {
        return houqi;
    }

    public void setHouqi(String houqi) {
        this.houqi = houqi;
    }

    @Basic
    @Column(name = "deputy_director", nullable = true, length = 32)
    public String getDeputyDirector() {
        return deputyDirector;
    }

    public void setDeputyDirector(String deputyDirector) {
        this.deputyDirector = deputyDirector;
    }

    @Basic
    @Column(name = "director", nullable = true, length = 32)
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Basic
    @Column(name = "process", nullable = true, length = -1)
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    @Basic
    @Column(name = "agreement_number", nullable = true, length = 100)
    public String getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    @Basic
    @Column(name = "customer_idcard", nullable = true, length = 18)
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
        Accepted0105 that = (Accepted0105) o;
        return id == that.id &&
                Objects.equal(acceptTime, that.acceptTime) &&
                Objects.equal(letterNumber, that.letterNumber) &&
                Objects.equal(acceptedNumber, that.acceptedNumber) &&
                Objects.equal(customerName, that.customerName) &&
                Objects.equal(customerPhone, that.customerPhone) &&
                Objects.equal(businessType, that.businessType) &&
                Objects.equal(agency, that.agency) &&
                Objects.equal(businessNature, that.businessNature) &&
                Objects.equal(wantMoney, that.wantMoney) &&
                Objects.equal(serviceFee, that.serviceFee) &&
                Objects.equal(serviceFeeActual, that.serviceFeeActual) &&
                Objects.equal(endDate, that.endDate) &&
                Objects.equal(clerk, that.clerk) &&
                Objects.equal(clerkName, that.clerkName) &&
                Objects.equal(state, that.state) &&
                Objects.equal(loanMoney, that.loanMoney) &&
                Objects.equal(serviceAgreement, that.serviceAgreement) &&
                Objects.equal(beizhu, that.beizhu) &&
                Objects.equal(createTime, that.createTime) &&
                Objects.equal(admin, that.admin) &&
                Objects.equal(houqi, that.houqi) &&
                Objects.equal(deputyDirector, that.deputyDirector) &&
                Objects.equal(director, that.director) &&
                Objects.equal(process, that.process) &&
                Objects.equal(agreementNumber, that.agreementNumber) &&
                Objects.equal(customerIdcard, that.customerIdcard);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, acceptTime, letterNumber, acceptedNumber, customerName, customerPhone, businessType, agency, businessNature, wantMoney, serviceFee, serviceFeeActual, endDate, clerk, clerkName, state, loanMoney, serviceAgreement, beizhu, createTime, admin, houqi, deputyDirector, director, process, agreementNumber, customerIdcard);
    }
}

package com.rccf.model;

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
    private Integer serviceFeeActual;
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
    public Integer getServiceFeeActual() {
        return serviceFeeActual;
    }

    public void setServiceFeeActual(Integer serviceFeeActual) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Accepted accepted = (Accepted) o;

        if (id != accepted.id) return false;
        if (acceptTime != null ? !acceptTime.equals(accepted.acceptTime) : accepted.acceptTime != null) return false;
        if (letterNumber != null ? !letterNumber.equals(accepted.letterNumber) : accepted.letterNumber != null)
            return false;
        if (acceptedNumber != null ? !acceptedNumber.equals(accepted.acceptedNumber) : accepted.acceptedNumber != null)
            return false;
        if (customerName != null ? !customerName.equals(accepted.customerName) : accepted.customerName != null)
            return false;
        if (customerPhone != null ? !customerPhone.equals(accepted.customerPhone) : accepted.customerPhone != null)
            return false;
        if (businessType != null ? !businessType.equals(accepted.businessType) : accepted.businessType != null)
            return false;
        if (agency != null ? !agency.equals(accepted.agency) : accepted.agency != null) return false;
        if (businessNature != null ? !businessNature.equals(accepted.businessNature) : accepted.businessNature != null)
            return false;
        if (wantMoney != null ? !wantMoney.equals(accepted.wantMoney) : accepted.wantMoney != null) return false;
        if (serviceFee != null ? !serviceFee.equals(accepted.serviceFee) : accepted.serviceFee != null) return false;
        if (serviceFeeActual != null ? !serviceFeeActual.equals(accepted.serviceFeeActual) : accepted.serviceFeeActual != null)
            return false;
        if (endDate != null ? !endDate.equals(accepted.endDate) : accepted.endDate != null) return false;
        if (clerk != null ? !clerk.equals(accepted.clerk) : accepted.clerk != null) return false;
        if (state != null ? !state.equals(accepted.state) : accepted.state != null) return false;
        if (loanMoney != null ? !loanMoney.equals(accepted.loanMoney) : accepted.loanMoney != null) return false;
        if (serviceAgreement != null ? !serviceAgreement.equals(accepted.serviceAgreement) : accepted.serviceAgreement != null)
            return false;
        if (beizhu != null ? !beizhu.equals(accepted.beizhu) : accepted.beizhu != null) return false;
        if (createTime != null ? !createTime.equals(accepted.createTime) : accepted.createTime != null) return false;
        if (admin != null ? !admin.equals(accepted.admin) : accepted.admin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (acceptTime != null ? acceptTime.hashCode() : 0);
        result = 31 * result + (letterNumber != null ? letterNumber.hashCode() : 0);
        result = 31 * result + (acceptedNumber != null ? acceptedNumber.hashCode() : 0);
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (customerPhone != null ? customerPhone.hashCode() : 0);
        result = 31 * result + (businessType != null ? businessType.hashCode() : 0);
        result = 31 * result + (agency != null ? agency.hashCode() : 0);
        result = 31 * result + (businessNature != null ? businessNature.hashCode() : 0);
        result = 31 * result + (wantMoney != null ? wantMoney.hashCode() : 0);
        result = 31 * result + (serviceFee != null ? serviceFee.hashCode() : 0);
        result = 31 * result + (serviceFeeActual != null ? serviceFeeActual.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (clerk != null ? clerk.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (loanMoney != null ? loanMoney.hashCode() : 0);
        result = 31 * result + (serviceAgreement != null ? serviceAgreement.hashCode() : 0);
        result = 31 * result + (beizhu != null ? beizhu.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (admin != null ? admin.hashCode() : 0);
        return result;
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
}

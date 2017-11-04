package com.rccf.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "r_customer_company", schema = "rccf", catalog = "")
public class RCustomerCompany {
    private int id;
    private String customerId;
    private String companyName;
    private String companyRegistAddress;
    private String companyOfficeAddress;
    private String companyRegistCapital;
    private Date companyEstablishedTime;
    private Date companyBusinessTime;
    private Integer companyNature;
    private String companyMainBusiness;
    private String companyPayCapital;
    private String companyEquity;

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
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "company_regist_address")
    public String getCompanyRegistAddress() {
        return companyRegistAddress;
    }

    public void setCompanyRegistAddress(String companyRegistAddress) {
        this.companyRegistAddress = companyRegistAddress;
    }

    @Basic
    @Column(name = "company_office_address")
    public String getCompanyOfficeAddress() {
        return companyOfficeAddress;
    }

    public void setCompanyOfficeAddress(String companyOfficeAddress) {
        this.companyOfficeAddress = companyOfficeAddress;
    }

    @Basic
    @Column(name = "company_regist_capital")
    public String getCompanyRegistCapital() {
        return companyRegistCapital;
    }

    public void setCompanyRegistCapital(String companyRegistCapital) {
        this.companyRegistCapital = companyRegistCapital;
    }

    @Basic
    @Column(name = "company_established_time")
    public Date getCompanyEstablishedTime() {
        return companyEstablishedTime;
    }

    public void setCompanyEstablishedTime(Date companyEstablishedTime) {
        this.companyEstablishedTime = companyEstablishedTime;
    }

    @Basic
    @Column(name = "company_business_time")
    public Date getCompanyBusinessTime() {
        return companyBusinessTime;
    }

    public void setCompanyBusinessTime(Date companyBusinessTime) {
        this.companyBusinessTime = companyBusinessTime;
    }

    @Basic
    @Column(name = "company_nature")
    public Integer getCompanyNature() {
        return companyNature;
    }

    public void setCompanyNature(Integer companyNature) {
        this.companyNature = companyNature;
    }

    @Basic
    @Column(name = "company_main_business")
    public String getCompanyMainBusiness() {
        return companyMainBusiness;
    }

    public void setCompanyMainBusiness(String companyMainBusiness) {
        this.companyMainBusiness = companyMainBusiness;
    }

    @Basic
    @Column(name = "company_pay_capital")
    public String getCompanyPayCapital() {
        return companyPayCapital;
    }

    public void setCompanyPayCapital(String companyPayCapital) {
        this.companyPayCapital = companyPayCapital;
    }

    @Basic
    @Column(name = "company_equity")
    public String getCompanyEquity() {
        return companyEquity;
    }

    public void setCompanyEquity(String companyEquity) {
        this.companyEquity = companyEquity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RCustomerCompany that = (RCustomerCompany) o;

        if (id != that.id) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (companyRegistAddress != null ? !companyRegistAddress.equals(that.companyRegistAddress) : that.companyRegistAddress != null)
            return false;
        if (companyOfficeAddress != null ? !companyOfficeAddress.equals(that.companyOfficeAddress) : that.companyOfficeAddress != null)
            return false;
        if (companyRegistCapital != null ? !companyRegistCapital.equals(that.companyRegistCapital) : that.companyRegistCapital != null)
            return false;
        if (companyEstablishedTime != null ? !companyEstablishedTime.equals(that.companyEstablishedTime) : that.companyEstablishedTime != null)
            return false;
        if (companyBusinessTime != null ? !companyBusinessTime.equals(that.companyBusinessTime) : that.companyBusinessTime != null)
            return false;
        if (companyNature != null ? !companyNature.equals(that.companyNature) : that.companyNature != null)
            return false;
        if (companyMainBusiness != null ? !companyMainBusiness.equals(that.companyMainBusiness) : that.companyMainBusiness != null)
            return false;
        if (companyPayCapital != null ? !companyPayCapital.equals(that.companyPayCapital) : that.companyPayCapital != null)
            return false;
        if (companyEquity != null ? !companyEquity.equals(that.companyEquity) : that.companyEquity != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (companyRegistAddress != null ? companyRegistAddress.hashCode() : 0);
        result = 31 * result + (companyOfficeAddress != null ? companyOfficeAddress.hashCode() : 0);
        result = 31 * result + (companyRegistCapital != null ? companyRegistCapital.hashCode() : 0);
        result = 31 * result + (companyEstablishedTime != null ? companyEstablishedTime.hashCode() : 0);
        result = 31 * result + (companyBusinessTime != null ? companyBusinessTime.hashCode() : 0);
        result = 31 * result + (companyNature != null ? companyNature.hashCode() : 0);
        result = 31 * result + (companyMainBusiness != null ? companyMainBusiness.hashCode() : 0);
        result = 31 * result + (companyPayCapital != null ? companyPayCapital.hashCode() : 0);
        result = 31 * result + (companyEquity != null ? companyEquity.hashCode() : 0);
        return result;
    }
}

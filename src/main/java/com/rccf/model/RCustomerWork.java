package com.rccf.model;

import javax.persistence.*;

@Entity
@Table(name = "r_customer_work", schema = "rccf", catalog = "")
public class RCustomerWork {
    private int id;
    private String customerId;
    private String cpmpanyName;
    private String companyTel;
    private String companyAddress;
    private Integer companyNature;
    private String companyDepartment;
    private String companyDuties;
    private Integer companySalary;

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
    @Column(name = "cpmpany_name")
    public String getCpmpanyName() {
        return cpmpanyName;
    }

    public void setCpmpanyName(String cpmpanyName) {
        this.cpmpanyName = cpmpanyName;
    }

    @Basic
    @Column(name = "company_tel")
    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    @Basic
    @Column(name = "company_address")
    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
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
    @Column(name = "company_department")
    public String getCompanyDepartment() {
        return companyDepartment;
    }

    public void setCompanyDepartment(String companyDepartment) {
        this.companyDepartment = companyDepartment;
    }

    @Basic
    @Column(name = "company_duties")
    public String getCompanyDuties() {
        return companyDuties;
    }

    public void setCompanyDuties(String companyDuties) {
        this.companyDuties = companyDuties;
    }

    @Basic
    @Column(name = "company_salary")
    public Integer getCompanySalary() {
        return companySalary;
    }

    public void setCompanySalary(Integer companySalary) {
        this.companySalary = companySalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RCustomerWork that = (RCustomerWork) o;

        if (id != that.id) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (cpmpanyName != null ? !cpmpanyName.equals(that.cpmpanyName) : that.cpmpanyName != null) return false;
        if (companyTel != null ? !companyTel.equals(that.companyTel) : that.companyTel != null) return false;
        if (companyAddress != null ? !companyAddress.equals(that.companyAddress) : that.companyAddress != null)
            return false;
        if (companyNature != null ? !companyNature.equals(that.companyNature) : that.companyNature != null)
            return false;
        if (companyDepartment != null ? !companyDepartment.equals(that.companyDepartment) : that.companyDepartment != null)
            return false;
        if (companyDuties != null ? !companyDuties.equals(that.companyDuties) : that.companyDuties != null)
            return false;
        if (companySalary != null ? !companySalary.equals(that.companySalary) : that.companySalary != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (cpmpanyName != null ? cpmpanyName.hashCode() : 0);
        result = 31 * result + (companyTel != null ? companyTel.hashCode() : 0);
        result = 31 * result + (companyAddress != null ? companyAddress.hashCode() : 0);
        result = 31 * result + (companyNature != null ? companyNature.hashCode() : 0);
        result = 31 * result + (companyDepartment != null ? companyDepartment.hashCode() : 0);
        result = 31 * result + (companyDuties != null ? companyDuties.hashCode() : 0);
        result = 31 * result + (companySalary != null ? companySalary.hashCode() : 0);
        return result;
    }
}

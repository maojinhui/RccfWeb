package com.rccf.model;

import javax.persistence.*;

@Entity
@Table(name = "r_customer_spouse", schema = "rccf", catalog = "")
public class RCustomerSpouse {
    private int id;
    private String customerId;
    private String spouseName;
    private Integer spouseAge;
    private Integer spousePhone;
    private String spouseIdcard;
    private String spouseCompanyName;
    private Integer spouseCompanyNature;
    private String spouseCompanyAddress;
    private String spouseCompanyTel;
    private String spouseCompanyDepartment;
    private String spouseCompanyDuties;
    private Integer spouseCompanySalary;

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
    @Column(name = "spouse_name")
    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    @Basic
    @Column(name = "spouse_age")
    public Integer getSpouseAge() {
        return spouseAge;
    }

    public void setSpouseAge(Integer spouseAge) {
        this.spouseAge = spouseAge;
    }

    @Basic
    @Column(name = "spouse_phone")
    public Integer getSpousePhone() {
        return spousePhone;
    }

    public void setSpousePhone(Integer spousePhone) {
        this.spousePhone = spousePhone;
    }

    @Basic
    @Column(name = "spouse_idcard")
    public String getSpouseIdcard() {
        return spouseIdcard;
    }

    public void setSpouseIdcard(String spouseIdcard) {
        this.spouseIdcard = spouseIdcard;
    }

    @Basic
    @Column(name = "spouse_company_name")
    public String getSpouseCompanyName() {
        return spouseCompanyName;
    }

    public void setSpouseCompanyName(String spouseCompanyName) {
        this.spouseCompanyName = spouseCompanyName;
    }

    @Basic
    @Column(name = "spouse_company_nature")
    public Integer getSpouseCompanyNature() {
        return spouseCompanyNature;
    }

    public void setSpouseCompanyNature(Integer spouseCompanyNature) {
        this.spouseCompanyNature = spouseCompanyNature;
    }

    @Basic
    @Column(name = "spouse_company_address")
    public String getSpouseCompanyAddress() {
        return spouseCompanyAddress;
    }

    public void setSpouseCompanyAddress(String spouseCompanyAddress) {
        this.spouseCompanyAddress = spouseCompanyAddress;
    }

    @Basic
    @Column(name = "spouse_company_tel")
    public String getSpouseCompanyTel() {
        return spouseCompanyTel;
    }

    public void setSpouseCompanyTel(String spouseCompanyTel) {
        this.spouseCompanyTel = spouseCompanyTel;
    }

    @Basic
    @Column(name = "spouse_company_department")
    public String getSpouseCompanyDepartment() {
        return spouseCompanyDepartment;
    }

    public void setSpouseCompanyDepartment(String spouseCompanyDepartment) {
        this.spouseCompanyDepartment = spouseCompanyDepartment;
    }

    @Basic
    @Column(name = "spouse_company_duties")
    public String getSpouseCompanyDuties() {
        return spouseCompanyDuties;
    }

    public void setSpouseCompanyDuties(String spouseCompanyDuties) {
        this.spouseCompanyDuties = spouseCompanyDuties;
    }

    @Basic
    @Column(name = "spouse_company_salary")
    public Integer getSpouseCompanySalary() {
        return spouseCompanySalary;
    }

    public void setSpouseCompanySalary(Integer spouseCompanySalary) {
        this.spouseCompanySalary = spouseCompanySalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RCustomerSpouse that = (RCustomerSpouse) o;

        if (id != that.id) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (spouseName != null ? !spouseName.equals(that.spouseName) : that.spouseName != null) return false;
        if (spouseAge != null ? !spouseAge.equals(that.spouseAge) : that.spouseAge != null) return false;
        if (spousePhone != null ? !spousePhone.equals(that.spousePhone) : that.spousePhone != null) return false;
        if (spouseIdcard != null ? !spouseIdcard.equals(that.spouseIdcard) : that.spouseIdcard != null) return false;
        if (spouseCompanyName != null ? !spouseCompanyName.equals(that.spouseCompanyName) : that.spouseCompanyName != null)
            return false;
        if (spouseCompanyNature != null ? !spouseCompanyNature.equals(that.spouseCompanyNature) : that.spouseCompanyNature != null)
            return false;
        if (spouseCompanyAddress != null ? !spouseCompanyAddress.equals(that.spouseCompanyAddress) : that.spouseCompanyAddress != null)
            return false;
        if (spouseCompanyTel != null ? !spouseCompanyTel.equals(that.spouseCompanyTel) : that.spouseCompanyTel != null)
            return false;
        if (spouseCompanyDepartment != null ? !spouseCompanyDepartment.equals(that.spouseCompanyDepartment) : that.spouseCompanyDepartment != null)
            return false;
        if (spouseCompanyDuties != null ? !spouseCompanyDuties.equals(that.spouseCompanyDuties) : that.spouseCompanyDuties != null)
            return false;
        if (spouseCompanySalary != null ? !spouseCompanySalary.equals(that.spouseCompanySalary) : that.spouseCompanySalary != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (spouseName != null ? spouseName.hashCode() : 0);
        result = 31 * result + (spouseAge != null ? spouseAge.hashCode() : 0);
        result = 31 * result + (spousePhone != null ? spousePhone.hashCode() : 0);
        result = 31 * result + (spouseIdcard != null ? spouseIdcard.hashCode() : 0);
        result = 31 * result + (spouseCompanyName != null ? spouseCompanyName.hashCode() : 0);
        result = 31 * result + (spouseCompanyNature != null ? spouseCompanyNature.hashCode() : 0);
        result = 31 * result + (spouseCompanyAddress != null ? spouseCompanyAddress.hashCode() : 0);
        result = 31 * result + (spouseCompanyTel != null ? spouseCompanyTel.hashCode() : 0);
        result = 31 * result + (spouseCompanyDepartment != null ? spouseCompanyDepartment.hashCode() : 0);
        result = 31 * result + (spouseCompanyDuties != null ? spouseCompanyDuties.hashCode() : 0);
        result = 31 * result + (spouseCompanySalary != null ? spouseCompanySalary.hashCode() : 0);
        return result;
    }
}

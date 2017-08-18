package com.rccf.model;

import javax.persistence.*;

@Entity
@Table(name = "product_diya", schema = "rccf", catalog = "")
public class ProductDiya {
    private int id;
    private String bianaho;
    private String emgency;
    private Integer personDo;
    private Integer companyDo;
    private Integer personMoney;
    private Integer companyMoney;
    private String lilv;
    private String chengshu;
    private Integer minLoanYear;
    private Integer maxLoanYear;
    private Integer minAge;
    private Integer maxAge;
    private Integer houseCompanyDo;
    private Integer houseYear;
    private String houseNature;
    private String houseArea;
    private Integer state;
    private Integer recommend;
    private Integer folkAffect;
    private String repaymentType;
    private Double personNumber;
    private Double companyNumber;
    private Double greatCompanyNumber;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bianaho")
    public String getBianaho() {
        return bianaho;
    }

    public void setBianaho(String bianaho) {
        this.bianaho = bianaho;
    }

    @Basic
    @Column(name = "emgency")
    public String getEmgency() {
        return emgency;
    }

    public void setEmgency(String emgency) {
        this.emgency = emgency;
    }

    @Basic
    @Column(name = "person_do")
    public Integer getPersonDo() {
        return personDo;
    }

    public void setPersonDo(Integer personDo) {
        this.personDo = personDo;
    }

    @Basic
    @Column(name = "company_do")
    public Integer getCompanyDo() {
        return companyDo;
    }

    public void setCompanyDo(Integer companyDo) {
        this.companyDo = companyDo;
    }

    @Basic
    @Column(name = "person_money")
    public Integer getPersonMoney() {
        return personMoney;
    }

    public void setPersonMoney(Integer personMoney) {
        this.personMoney = personMoney;
    }

    @Basic
    @Column(name = "company_money")
    public Integer getCompanyMoney() {
        return companyMoney;
    }

    public void setCompanyMoney(Integer companyMoney) {
        this.companyMoney = companyMoney;
    }

    @Basic
    @Column(name = "lilv")
    public String getLilv() {
        return lilv;
    }

    public void setLilv(String lilv) {
        this.lilv = lilv;
    }

    @Basic
    @Column(name = "chengshu")
    public String getChengshu() {
        return chengshu;
    }

    public void setChengshu(String chengshu) {
        this.chengshu = chengshu;
    }

    @Basic
    @Column(name = "min_loan_year")
    public Integer getMinLoanYear() {
        return minLoanYear;
    }

    public void setMinLoanYear(Integer minLoanYear) {
        this.minLoanYear = minLoanYear;
    }

    @Basic
    @Column(name = "max_loan_year")
    public Integer getMaxLoanYear() {
        return maxLoanYear;
    }

    public void setMaxLoanYear(Integer maxLoanYear) {
        this.maxLoanYear = maxLoanYear;
    }

    @Basic
    @Column(name = "min_age")
    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    @Basic
    @Column(name = "max_age")
    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    @Basic
    @Column(name = "house_company_do")
    public Integer getHouseCompanyDo() {
        return houseCompanyDo;
    }

    public void setHouseCompanyDo(Integer houseCompanyDo) {
        this.houseCompanyDo = houseCompanyDo;
    }

    @Basic
    @Column(name = "house_year")
    public Integer getHouseYear() {
        return houseYear;
    }

    public void setHouseYear(Integer houseYear) {
        this.houseYear = houseYear;
    }

    @Basic
    @Column(name = "house_nature")
    public String getHouseNature() {
        return houseNature;
    }

    public void setHouseNature(String houseNature) {
        this.houseNature = houseNature;
    }

    @Basic
    @Column(name = "house_area")
    public String getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(String houseArea) {
        this.houseArea = houseArea;
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
    @Column(name = "recommend")
    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    @Basic
    @Column(name = "folk_affect")
    public Integer getFolkAffect() {
        return folkAffect;
    }

    public void setFolkAffect(Integer folkAffect) {
        this.folkAffect = folkAffect;
    }

    @Basic
    @Column(name = "repayment_type")
    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDiya that = (ProductDiya) o;

        if (id != that.id) return false;
        if (bianaho != null ? !bianaho.equals(that.bianaho) : that.bianaho != null) return false;
        if (emgency != null ? !emgency.equals(that.emgency) : that.emgency != null) return false;
        if (personDo != null ? !personDo.equals(that.personDo) : that.personDo != null) return false;
        if (companyDo != null ? !companyDo.equals(that.companyDo) : that.companyDo != null) return false;
        if (personMoney != null ? !personMoney.equals(that.personMoney) : that.personMoney != null) return false;
        if (companyMoney != null ? !companyMoney.equals(that.companyMoney) : that.companyMoney != null) return false;
        if (lilv != null ? !lilv.equals(that.lilv) : that.lilv != null) return false;
        if (chengshu != null ? !chengshu.equals(that.chengshu) : that.chengshu != null) return false;
        if (minLoanYear != null ? !minLoanYear.equals(that.minLoanYear) : that.minLoanYear != null) return false;
        if (maxLoanYear != null ? !maxLoanYear.equals(that.maxLoanYear) : that.maxLoanYear != null) return false;
        if (minAge != null ? !minAge.equals(that.minAge) : that.minAge != null) return false;
        if (maxAge != null ? !maxAge.equals(that.maxAge) : that.maxAge != null) return false;
        if (houseCompanyDo != null ? !houseCompanyDo.equals(that.houseCompanyDo) : that.houseCompanyDo != null)
            return false;
        if (houseYear != null ? !houseYear.equals(that.houseYear) : that.houseYear != null) return false;
        if (houseNature != null ? !houseNature.equals(that.houseNature) : that.houseNature != null) return false;
        if (houseArea != null ? !houseArea.equals(that.houseArea) : that.houseArea != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (recommend != null ? !recommend.equals(that.recommend) : that.recommend != null) return false;
        if (folkAffect != null ? !folkAffect.equals(that.folkAffect) : that.folkAffect != null) return false;
        if (repaymentType != null ? !repaymentType.equals(that.repaymentType) : that.repaymentType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (bianaho != null ? bianaho.hashCode() : 0);
        result = 31 * result + (emgency != null ? emgency.hashCode() : 0);
        result = 31 * result + (personDo != null ? personDo.hashCode() : 0);
        result = 31 * result + (companyDo != null ? companyDo.hashCode() : 0);
        result = 31 * result + (personMoney != null ? personMoney.hashCode() : 0);
        result = 31 * result + (companyMoney != null ? companyMoney.hashCode() : 0);
        result = 31 * result + (lilv != null ? lilv.hashCode() : 0);
        result = 31 * result + (chengshu != null ? chengshu.hashCode() : 0);
        result = 31 * result + (minLoanYear != null ? minLoanYear.hashCode() : 0);
        result = 31 * result + (maxLoanYear != null ? maxLoanYear.hashCode() : 0);
        result = 31 * result + (minAge != null ? minAge.hashCode() : 0);
        result = 31 * result + (maxAge != null ? maxAge.hashCode() : 0);
        result = 31 * result + (houseCompanyDo != null ? houseCompanyDo.hashCode() : 0);
        result = 31 * result + (houseYear != null ? houseYear.hashCode() : 0);
        result = 31 * result + (houseNature != null ? houseNature.hashCode() : 0);
        result = 31 * result + (houseArea != null ? houseArea.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (recommend != null ? recommend.hashCode() : 0);
        result = 31 * result + (folkAffect != null ? folkAffect.hashCode() : 0);
        result = 31 * result + (repaymentType != null ? repaymentType.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "person_number")
    public Double getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(Double personNumber) {
        this.personNumber = personNumber;
    }

    @Basic
    @Column(name = "company_number")
    public Double getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(Double companyNumber) {
        this.companyNumber = companyNumber;
    }

    @Basic
    @Column(name = "great_company_number")
    public Double getGreatCompanyNumber() {
        return greatCompanyNumber;
    }

    public void setGreatCompanyNumber(Double greatCompanyNumber) {
        this.greatCompanyNumber = greatCompanyNumber;
    }
}

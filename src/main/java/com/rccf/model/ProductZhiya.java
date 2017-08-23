package com.rccf.model;

import javax.persistence.*;

@Entity
@Table(name = "product_zhiya", schema = "rccf", catalog = "")
public class ProductZhiya {
    private int id;
    private String bianhao;
    private String emgency;
    private Double moneyNumber;
    private Double villaNumber;
    private Integer amountMoneyOne;
    private Integer amountMoneyMore;
    private String repayment;
    private String houseArea;
    private String houseNature;
    private Integer houseAge;
    private String material;
    private Integer minAge;
    private Integer maxAge;
    private String ageAdded;
    private Integer minLoanYear;
    private Integer maxLoanYear;
    private String loanRate;
    private Integer ddby;
    private Integer erdiDo;
    private Integer extension;
    private String extensionFee;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bianhao")
    public String getBianhao() {
        return bianhao;
    }

    public void setBianhao(String bianhao) {
        this.bianhao = bianhao;
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
    @Column(name = "money_number")
    public Double getMoneyNumber() {
        return moneyNumber;
    }

    public void setMoneyNumber(Double moneyNumber) {
        this.moneyNumber = moneyNumber;
    }

    @Basic
    @Column(name = "villa_number")
    public Double getVillaNumber() {
        return villaNumber;
    }

    public void setVillaNumber(Double villaNumber) {
        this.villaNumber = villaNumber;
    }

    @Basic
    @Column(name = "amount_money_one")
    public Integer getAmountMoneyOne() {
        return amountMoneyOne;
    }

    public void setAmountMoneyOne(Integer amountMoneyOne) {
        this.amountMoneyOne = amountMoneyOne;
    }

    @Basic
    @Column(name = "amount_money_more")
    public Integer getAmountMoneyMore() {
        return amountMoneyMore;
    }

    public void setAmountMoneyMore(Integer amountMoneyMore) {
        this.amountMoneyMore = amountMoneyMore;
    }

    @Basic
    @Column(name = "repayment")
    public String getRepayment() {
        return repayment;
    }

    public void setRepayment(String repayment) {
        this.repayment = repayment;
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
    @Column(name = "house_nature")
    public String getHouseNature() {
        return houseNature;
    }

    public void setHouseNature(String houseNature) {
        this.houseNature = houseNature;
    }

    @Basic
    @Column(name = "house_age")
    public Integer getHouseAge() {
        return houseAge;
    }

    public void setHouseAge(Integer houseAge) {
        this.houseAge = houseAge;
    }

    @Basic
    @Column(name = "material")
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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
    @Column(name = "age_added")
    public String getAgeAdded() {
        return ageAdded;
    }

    public void setAgeAdded(String ageAdded) {
        this.ageAdded = ageAdded;
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
    @Column(name = "loan_rate")
    public String getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(String loanRate) {
        this.loanRate = loanRate;
    }

    @Basic
    @Column(name = "ddby")
    public Integer getDdby() {
        return ddby;
    }

    public void setDdby(Integer ddby) {
        this.ddby = ddby;
    }

    @Basic
    @Column(name = "erdi_do")
    public Integer getErdiDo() {
        return erdiDo;
    }

    public void setErdiDo(Integer erdiDo) {
        this.erdiDo = erdiDo;
    }

    @Basic
    @Column(name = "extension")
    public Integer getExtension() {
        return extension;
    }

    public void setExtension(Integer extension) {
        this.extension = extension;
    }

    @Basic
    @Column(name = "extension_fee")
    public String getExtensionFee() {
        return extensionFee;
    }

    public void setExtensionFee(String extensionFee) {
        this.extensionFee = extensionFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductZhiya that = (ProductZhiya) o;

        if (id != that.id) return false;
        if (bianhao != null ? !bianhao.equals(that.bianhao) : that.bianhao != null) return false;
        if (emgency != null ? !emgency.equals(that.emgency) : that.emgency != null) return false;
        if (moneyNumber != null ? !moneyNumber.equals(that.moneyNumber) : that.moneyNumber != null) return false;
        if (villaNumber != null ? !villaNumber.equals(that.villaNumber) : that.villaNumber != null) return false;
        if (amountMoneyOne != null ? !amountMoneyOne.equals(that.amountMoneyOne) : that.amountMoneyOne != null)
            return false;
        if (amountMoneyMore != null ? !amountMoneyMore.equals(that.amountMoneyMore) : that.amountMoneyMore != null)
            return false;
        if (repayment != null ? !repayment.equals(that.repayment) : that.repayment != null) return false;
        if (houseArea != null ? !houseArea.equals(that.houseArea) : that.houseArea != null) return false;
        if (houseNature != null ? !houseNature.equals(that.houseNature) : that.houseNature != null) return false;
        if (houseAge != null ? !houseAge.equals(that.houseAge) : that.houseAge != null) return false;
        if (material != null ? !material.equals(that.material) : that.material != null) return false;
        if (minAge != null ? !minAge.equals(that.minAge) : that.minAge != null) return false;
        if (maxAge != null ? !maxAge.equals(that.maxAge) : that.maxAge != null) return false;
        if (ageAdded != null ? !ageAdded.equals(that.ageAdded) : that.ageAdded != null) return false;
        if (minLoanYear != null ? !minLoanYear.equals(that.minLoanYear) : that.minLoanYear != null) return false;
        if (maxLoanYear != null ? !maxLoanYear.equals(that.maxLoanYear) : that.maxLoanYear != null) return false;
        if (loanRate != null ? !loanRate.equals(that.loanRate) : that.loanRate != null) return false;
        if (ddby != null ? !ddby.equals(that.ddby) : that.ddby != null) return false;
        if (erdiDo != null ? !erdiDo.equals(that.erdiDo) : that.erdiDo != null) return false;
        if (extension != null ? !extension.equals(that.extension) : that.extension != null) return false;
        if (extensionFee != null ? !extensionFee.equals(that.extensionFee) : that.extensionFee != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (bianhao != null ? bianhao.hashCode() : 0);
        result = 31 * result + (emgency != null ? emgency.hashCode() : 0);
        result = 31 * result + (moneyNumber != null ? moneyNumber.hashCode() : 0);
        result = 31 * result + (villaNumber != null ? villaNumber.hashCode() : 0);
        result = 31 * result + (amountMoneyOne != null ? amountMoneyOne.hashCode() : 0);
        result = 31 * result + (amountMoneyMore != null ? amountMoneyMore.hashCode() : 0);
        result = 31 * result + (repayment != null ? repayment.hashCode() : 0);
        result = 31 * result + (houseArea != null ? houseArea.hashCode() : 0);
        result = 31 * result + (houseNature != null ? houseNature.hashCode() : 0);
        result = 31 * result + (houseAge != null ? houseAge.hashCode() : 0);
        result = 31 * result + (material != null ? material.hashCode() : 0);
        result = 31 * result + (minAge != null ? minAge.hashCode() : 0);
        result = 31 * result + (maxAge != null ? maxAge.hashCode() : 0);
        result = 31 * result + (ageAdded != null ? ageAdded.hashCode() : 0);
        result = 31 * result + (minLoanYear != null ? minLoanYear.hashCode() : 0);
        result = 31 * result + (maxLoanYear != null ? maxLoanYear.hashCode() : 0);
        result = 31 * result + (loanRate != null ? loanRate.hashCode() : 0);
        result = 31 * result + (ddby != null ? ddby.hashCode() : 0);
        result = 31 * result + (erdiDo != null ? erdiDo.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        result = 31 * result + (extensionFee != null ? extensionFee.hashCode() : 0);
        return result;
    }
}

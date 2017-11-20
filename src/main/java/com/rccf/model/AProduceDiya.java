package com.rccf.model;

import javax.persistence.*;

@Entity
@Table(name = "a_produce_diya", schema = "rccf", catalog = "")
public class AProduceDiya {
    private int id;
    private String name;
    private String code;
    private Integer agencyId;
    private String loanPeople;
    private String repaymentType;
    private Integer loanBidType;
    private String loanScale;
    private String loanSacelOther;
    private String houseArea;
    private String houseAreaOther;
    private String loanAmountTao;
    private String loanAmount;
    private String loanAmountOther;
    private String loanRate;
    private String loanRateOther;
    private String personMaterial;
    private String companyMaterial;
    private Integer minAge;
    private Integer maxAge;
    private String ageOther;
    private Integer minMonth;
    private Integer mixMonth;
    private String fixedMonth;
    private String diyaType;
    private String houseOwnership;
    private String applyLoanType;
    private Integer applyHouseAge;
    private String applyHouseNature;
    private Integer folkMortgageAffect;
    private Integer loanMaxHouseageplusloanyear;
    private Integer loanMaxHouseageplusloanyearOther;
    private Integer differentLoanMortgage;
    private String processDetails;
    private String advantage;
    private String disadvantage;
    private String notice;
    private String shootReason;
    private Integer state;
    private String agencyName;

    @Id
    @Column(name = "id")

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "agency_id")
    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    @Basic
    @Column(name = "loan_people")
    public String getLoanPeople() {
        return loanPeople;
    }

    public void setLoanPeople(String loanPeople) {
        this.loanPeople = loanPeople;
    }

    @Basic
    @Column(name = "repayment_type")
    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }

    @Basic
    @Column(name = "loan_bid_type")
    public Integer getLoanBidType() {
        return loanBidType;
    }

    public void setLoanBidType(Integer loanBidType) {
        this.loanBidType = loanBidType;
    }

    @Basic
    @Column(name = "loan_scale")
    public String getLoanScale() {
        return loanScale;
    }

    public void setLoanScale(String loanScale) {
        this.loanScale = loanScale;
    }

    @Basic
    @Column(name = "loan_sacel_other")
    public String getLoanSacelOther() {
        return loanSacelOther;
    }

    public void setLoanSacelOther(String loanSacelOther) {
        this.loanSacelOther = loanSacelOther;
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
    @Column(name = "house_area_other")
    public String getHouseAreaOther() {
        return houseAreaOther;
    }

    public void setHouseAreaOther(String houseAreaOther) {
        this.houseAreaOther = houseAreaOther;
    }

    @Basic
    @Column(name = "loan_amount_tao")
    public String getLoanAmountTao() {
        return loanAmountTao;
    }

    public void setLoanAmountTao(String loanAmountTao) {
        this.loanAmountTao = loanAmountTao;
    }

    @Basic
    @Column(name = "loan_amount")
    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    @Basic
    @Column(name = "loan_amount_other")
    public String getLoanAmountOther() {
        return loanAmountOther;
    }

    public void setLoanAmountOther(String loanAmountOther) {
        this.loanAmountOther = loanAmountOther;
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
    @Column(name = "loan_rate_other")
    public String getLoanRateOther() {
        return loanRateOther;
    }

    public void setLoanRateOther(String loanRateOther) {
        this.loanRateOther = loanRateOther;
    }

    @Basic
    @Column(name = "person_material")
    public String getPersonMaterial() {
        return personMaterial;
    }

    public void setPersonMaterial(String personMaterial) {
        this.personMaterial = personMaterial;
    }

    @Basic
    @Column(name = "company_material")
    public String getCompanyMaterial() {
        return companyMaterial;
    }

    public void setCompanyMaterial(String companyMaterial) {
        this.companyMaterial = companyMaterial;
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
    @Column(name = "age_other")
    public String getAgeOther() {
        return ageOther;
    }

    public void setAgeOther(String ageOther) {
        this.ageOther = ageOther;
    }

    @Basic
    @Column(name = "min_month")
    public Integer getMinMonth() {
        return minMonth;
    }

    public void setMinMonth(Integer minMonth) {
        this.minMonth = minMonth;
    }

    @Basic
    @Column(name = "mix_month")
    public Integer getMixMonth() {
        return mixMonth;
    }

    public void setMixMonth(Integer mixMonth) {
        this.mixMonth = mixMonth;
    }

    @Basic
    @Column(name = "fixed_month")
    public String getFixedMonth() {
        return fixedMonth;
    }

    public void setFixedMonth(String fixedMonth) {
        this.fixedMonth = fixedMonth;
    }

    @Basic
    @Column(name = "diya_type")
    public String getDiyaType() {
        return diyaType;
    }

    public void setDiyaType(String diyaType) {
        this.diyaType = diyaType;
    }

    @Basic
    @Column(name = "house_ownership")
    public String getHouseOwnership() {
        return houseOwnership;
    }

    public void setHouseOwnership(String houseOwnership) {
        this.houseOwnership = houseOwnership;
    }

    @Basic
    @Column(name = "apply_loan_type")
    public String getApplyLoanType() {
        return applyLoanType;
    }

    public void setApplyLoanType(String applyLoanType) {
        this.applyLoanType = applyLoanType;
    }

    @Basic
    @Column(name = "apply_house_age")
    public Integer getApplyHouseAge() {
        return applyHouseAge;
    }

    public void setApplyHouseAge(Integer applyHouseAge) {
        this.applyHouseAge = applyHouseAge;
    }

    @Basic
    @Column(name = "apply_house_nature")
    public String getApplyHouseNature() {
        return applyHouseNature;
    }

    public void setApplyHouseNature(String applyHouseNature) {
        this.applyHouseNature = applyHouseNature;
    }

    @Basic
    @Column(name = "folk_mortgage_affect")
    public Integer getFolkMortgageAffect() {
        return folkMortgageAffect;
    }

    public void setFolkMortgageAffect(Integer folkMortgageAffect) {
        this.folkMortgageAffect = folkMortgageAffect;
    }

    @Basic
    @Column(name = "loan_max_houseageplusloanyear")
    public Integer getLoanMaxHouseageplusloanyear() {
        return loanMaxHouseageplusloanyear;
    }

    public void setLoanMaxHouseageplusloanyear(Integer loanMaxHouseageplusloanyear) {
        this.loanMaxHouseageplusloanyear = loanMaxHouseageplusloanyear;
    }

    @Basic
    @Column(name = "loan_max_houseageplusloanyear_other")
    public Integer getLoanMaxHouseageplusloanyearOther() {
        return loanMaxHouseageplusloanyearOther;
    }

    public void setLoanMaxHouseageplusloanyearOther(Integer loanMaxHouseageplusloanyearOther) {
        this.loanMaxHouseageplusloanyearOther = loanMaxHouseageplusloanyearOther;
    }

    @Basic
    @Column(name = "different_loan_mortgage")
    public Integer getDifferentLoanMortgage() {
        return differentLoanMortgage;
    }

    public void setDifferentLoanMortgage(Integer differentLoanMortgage) {
        this.differentLoanMortgage = differentLoanMortgage;
    }

    @Basic
    @Column(name = "process_details")
    public String getProcessDetails() {
        return processDetails;
    }

    public void setProcessDetails(String processDetails) {
        this.processDetails = processDetails;
    }

    @Basic
    @Column(name = "advantage")
    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    @Basic
    @Column(name = "disadvantage")
    public String getDisadvantage() {
        return disadvantage;
    }

    public void setDisadvantage(String disadvantage) {
        this.disadvantage = disadvantage;
    }

    @Basic
    @Column(name = "notice")
    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    @Basic
    @Column(name = "shoot_reason")
    public String getShootReason() {
        return shootReason;
    }

    public void setShootReason(String shootReason) {
        this.shootReason = shootReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AProduceDiya that = (AProduceDiya) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (agencyId != null ? !agencyId.equals(that.agencyId) : that.agencyId != null) return false;
        if (loanPeople != null ? !loanPeople.equals(that.loanPeople) : that.loanPeople != null) return false;
        if (repaymentType != null ? !repaymentType.equals(that.repaymentType) : that.repaymentType != null)
            return false;
        if (loanBidType != null ? !loanBidType.equals(that.loanBidType) : that.loanBidType != null) return false;
        if (loanScale != null ? !loanScale.equals(that.loanScale) : that.loanScale != null) return false;
        if (loanSacelOther != null ? !loanSacelOther.equals(that.loanSacelOther) : that.loanSacelOther != null)
            return false;
        if (houseArea != null ? !houseArea.equals(that.houseArea) : that.houseArea != null) return false;
        if (houseAreaOther != null ? !houseAreaOther.equals(that.houseAreaOther) : that.houseAreaOther != null)
            return false;
        if (loanAmountTao != null ? !loanAmountTao.equals(that.loanAmountTao) : that.loanAmountTao != null)
            return false;
        if (loanAmount != null ? !loanAmount.equals(that.loanAmount) : that.loanAmount != null) return false;
        if (loanAmountOther != null ? !loanAmountOther.equals(that.loanAmountOther) : that.loanAmountOther != null)
            return false;
        if (loanRate != null ? !loanRate.equals(that.loanRate) : that.loanRate != null) return false;
        if (loanRateOther != null ? !loanRateOther.equals(that.loanRateOther) : that.loanRateOther != null)
            return false;
        if (personMaterial != null ? !personMaterial.equals(that.personMaterial) : that.personMaterial != null)
            return false;
        if (companyMaterial != null ? !companyMaterial.equals(that.companyMaterial) : that.companyMaterial != null)
            return false;
        if (minAge != null ? !minAge.equals(that.minAge) : that.minAge != null) return false;
        if (maxAge != null ? !maxAge.equals(that.maxAge) : that.maxAge != null) return false;
        if (ageOther != null ? !ageOther.equals(that.ageOther) : that.ageOther != null) return false;
        if (minMonth != null ? !minMonth.equals(that.minMonth) : that.minMonth != null) return false;
        if (mixMonth != null ? !mixMonth.equals(that.mixMonth) : that.mixMonth != null) return false;
        if (fixedMonth != null ? !fixedMonth.equals(that.fixedMonth) : that.fixedMonth != null) return false;
        if (diyaType != null ? !diyaType.equals(that.diyaType) : that.diyaType != null) return false;
        if (houseOwnership != null ? !houseOwnership.equals(that.houseOwnership) : that.houseOwnership != null)
            return false;
        if (applyLoanType != null ? !applyLoanType.equals(that.applyLoanType) : that.applyLoanType != null)
            return false;
        if (applyHouseAge != null ? !applyHouseAge.equals(that.applyHouseAge) : that.applyHouseAge != null)
            return false;
        if (applyHouseNature != null ? !applyHouseNature.equals(that.applyHouseNature) : that.applyHouseNature != null)
            return false;
        if (folkMortgageAffect != null ? !folkMortgageAffect.equals(that.folkMortgageAffect) : that.folkMortgageAffect != null)
            return false;
        if (loanMaxHouseageplusloanyear != null ? !loanMaxHouseageplusloanyear.equals(that.loanMaxHouseageplusloanyear) : that.loanMaxHouseageplusloanyear != null)
            return false;
        if (loanMaxHouseageplusloanyearOther != null ? !loanMaxHouseageplusloanyearOther.equals(that.loanMaxHouseageplusloanyearOther) : that.loanMaxHouseageplusloanyearOther != null)
            return false;
        if (differentLoanMortgage != null ? !differentLoanMortgage.equals(that.differentLoanMortgage) : that.differentLoanMortgage != null)
            return false;
        if (processDetails != null ? !processDetails.equals(that.processDetails) : that.processDetails != null)
            return false;
        if (advantage != null ? !advantage.equals(that.advantage) : that.advantage != null) return false;
        if (disadvantage != null ? !disadvantage.equals(that.disadvantage) : that.disadvantage != null) return false;
        if (notice != null ? !notice.equals(that.notice) : that.notice != null) return false;
        if (shootReason != null ? !shootReason.equals(that.shootReason) : that.shootReason != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (agencyId != null ? agencyId.hashCode() : 0);
        result = 31 * result + (loanPeople != null ? loanPeople.hashCode() : 0);
        result = 31 * result + (repaymentType != null ? repaymentType.hashCode() : 0);
        result = 31 * result + (loanBidType != null ? loanBidType.hashCode() : 0);
        result = 31 * result + (loanScale != null ? loanScale.hashCode() : 0);
        result = 31 * result + (loanSacelOther != null ? loanSacelOther.hashCode() : 0);
        result = 31 * result + (houseArea != null ? houseArea.hashCode() : 0);
        result = 31 * result + (houseAreaOther != null ? houseAreaOther.hashCode() : 0);
        result = 31 * result + (loanAmountTao != null ? loanAmountTao.hashCode() : 0);
        result = 31 * result + (loanAmount != null ? loanAmount.hashCode() : 0);
        result = 31 * result + (loanAmountOther != null ? loanAmountOther.hashCode() : 0);
        result = 31 * result + (loanRate != null ? loanRate.hashCode() : 0);
        result = 31 * result + (loanRateOther != null ? loanRateOther.hashCode() : 0);
        result = 31 * result + (personMaterial != null ? personMaterial.hashCode() : 0);
        result = 31 * result + (companyMaterial != null ? companyMaterial.hashCode() : 0);
        result = 31 * result + (minAge != null ? minAge.hashCode() : 0);
        result = 31 * result + (maxAge != null ? maxAge.hashCode() : 0);
        result = 31 * result + (ageOther != null ? ageOther.hashCode() : 0);
        result = 31 * result + (minMonth != null ? minMonth.hashCode() : 0);
        result = 31 * result + (mixMonth != null ? mixMonth.hashCode() : 0);
        result = 31 * result + (fixedMonth != null ? fixedMonth.hashCode() : 0);
        result = 31 * result + (diyaType != null ? diyaType.hashCode() : 0);
        result = 31 * result + (houseOwnership != null ? houseOwnership.hashCode() : 0);
        result = 31 * result + (applyLoanType != null ? applyLoanType.hashCode() : 0);
        result = 31 * result + (applyHouseAge != null ? applyHouseAge.hashCode() : 0);
        result = 31 * result + (applyHouseNature != null ? applyHouseNature.hashCode() : 0);
        result = 31 * result + (folkMortgageAffect != null ? folkMortgageAffect.hashCode() : 0);
        result = 31 * result + (loanMaxHouseageplusloanyear != null ? loanMaxHouseageplusloanyear.hashCode() : 0);
        result = 31 * result + (loanMaxHouseageplusloanyearOther != null ? loanMaxHouseageplusloanyearOther.hashCode() : 0);
        result = 31 * result + (differentLoanMortgage != null ? differentLoanMortgage.hashCode() : 0);
        result = 31 * result + (processDetails != null ? processDetails.hashCode() : 0);
        result = 31 * result + (advantage != null ? advantage.hashCode() : 0);
        result = 31 * result + (disadvantage != null ? disadvantage.hashCode() : 0);
        result = 31 * result + (notice != null ? notice.hashCode() : 0);
        result = 31 * result + (shootReason != null ? shootReason.hashCode() : 0);
        return result;
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
    @Column(name = "agency_name")
    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
}

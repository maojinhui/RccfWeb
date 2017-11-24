package com.rccf.model.produce;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "a_produce_zhiya", schema = "rccf", catalog = "")
public class AProduceZhiya {
    private int id;
    private String name;
    private String code;
    private Integer agencyId;
    private String agencyName;
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
    private Integer supportExtension;
    private String extensionFee;
    private String loanRate;
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
    private Timestamp createTime;
    private String recommend;

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
    @Column(name = "agency_name")
    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
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
    @Column(name = "support_extension")
    public Integer getSupportExtension() {
        return supportExtension;
    }

    public void setSupportExtension(Integer supportExtension) {
        this.supportExtension = supportExtension;
    }

    @Basic
    @Column(name = "extension_fee")
    public String getExtensionFee() {
        return extensionFee;
    }

    public void setExtensionFee(String extensionFee) {
        this.extensionFee = extensionFee;
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

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
    @Column(name = "recommend")
    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AProduceZhiya that = (AProduceZhiya) o;
        return id == that.id &&
                Objects.equal(name, that.name) &&
                Objects.equal(code, that.code) &&
                Objects.equal(agencyId, that.agencyId) &&
                Objects.equal(agencyName, that.agencyName) &&
                Objects.equal(loanPeople, that.loanPeople) &&
                Objects.equal(repaymentType, that.repaymentType) &&
                Objects.equal(loanBidType, that.loanBidType) &&
                Objects.equal(loanScale, that.loanScale) &&
                Objects.equal(loanSacelOther, that.loanSacelOther) &&
                Objects.equal(houseArea, that.houseArea) &&
                Objects.equal(houseAreaOther, that.houseAreaOther) &&
                Objects.equal(loanAmountTao, that.loanAmountTao) &&
                Objects.equal(loanAmount, that.loanAmount) &&
                Objects.equal(loanAmountOther, that.loanAmountOther) &&
                Objects.equal(supportExtension, that.supportExtension) &&
                Objects.equal(extensionFee, that.extensionFee) &&
                Objects.equal(loanRate, that.loanRate) &&
                Objects.equal(personMaterial, that.personMaterial) &&
                Objects.equal(companyMaterial, that.companyMaterial) &&
                Objects.equal(minAge, that.minAge) &&
                Objects.equal(maxAge, that.maxAge) &&
                Objects.equal(ageOther, that.ageOther) &&
                Objects.equal(minMonth, that.minMonth) &&
                Objects.equal(mixMonth, that.mixMonth) &&
                Objects.equal(fixedMonth, that.fixedMonth) &&
                Objects.equal(diyaType, that.diyaType) &&
                Objects.equal(houseOwnership, that.houseOwnership) &&
                Objects.equal(applyLoanType, that.applyLoanType) &&
                Objects.equal(applyHouseAge, that.applyHouseAge) &&
                Objects.equal(applyHouseNature, that.applyHouseNature) &&
                Objects.equal(folkMortgageAffect, that.folkMortgageAffect) &&
                Objects.equal(loanMaxHouseageplusloanyear, that.loanMaxHouseageplusloanyear) &&
                Objects.equal(loanMaxHouseageplusloanyearOther, that.loanMaxHouseageplusloanyearOther) &&
                Objects.equal(differentLoanMortgage, that.differentLoanMortgage) &&
                Objects.equal(processDetails, that.processDetails) &&
                Objects.equal(advantage, that.advantage) &&
                Objects.equal(disadvantage, that.disadvantage) &&
                Objects.equal(notice, that.notice) &&
                Objects.equal(shootReason, that.shootReason) &&
                Objects.equal(state, that.state) &&
                Objects.equal(createTime, that.createTime) &&
                Objects.equal(recommend, that.recommend);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, code, agencyId, agencyName, loanPeople, repaymentType, loanBidType, loanScale, loanSacelOther, houseArea, houseAreaOther, loanAmountTao, loanAmount, loanAmountOther, supportExtension, extensionFee, loanRate, personMaterial, companyMaterial, minAge, maxAge, ageOther, minMonth, mixMonth, fixedMonth, diyaType, houseOwnership, applyLoanType, applyHouseAge, applyHouseNature, folkMortgageAffect, loanMaxHouseageplusloanyear, loanMaxHouseageplusloanyearOther, differentLoanMortgage, processDetails, advantage, disadvantage, notice, shootReason, state, createTime, recommend);
    }
}

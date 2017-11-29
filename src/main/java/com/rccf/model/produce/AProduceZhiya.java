package com.rccf.model.produce;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "a_produce_zhiya", schema = "rccf", catalog = "")
public class AProduceZhiya extends BaseProduct{
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
    private Integer createPerson;
    private Integer log;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Basic
    @Column(name = "create_person")
    public Integer getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Integer createPerson) {
        this.createPerson = createPerson;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, code, agencyId, agencyName, loanPeople, repaymentType, loanBidType, loanScale, loanSacelOther, houseArea, houseAreaOther, loanAmountTao, loanAmount, loanAmountOther, supportExtension, extensionFee, loanRate, personMaterial, companyMaterial, minAge, maxAge, ageOther, minMonth, mixMonth, fixedMonth, diyaType, houseOwnership, applyLoanType, applyHouseAge, applyHouseNature, folkMortgageAffect, loanMaxHouseageplusloanyear, loanMaxHouseageplusloanyearOther, differentLoanMortgage, processDetails, advantage, disadvantage, notice, shootReason, state, createTime, recommend, createPerson);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final AProduceZhiya other = (AProduceZhiya) obj;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.name, other.name)
                && Objects.equal(this.code, other.code)
                && Objects.equal(this.agencyId, other.agencyId)
                && Objects.equal(this.agencyName, other.agencyName)
                && Objects.equal(this.loanPeople, other.loanPeople)
                && Objects.equal(this.repaymentType, other.repaymentType)
                && Objects.equal(this.loanBidType, other.loanBidType)
                && Objects.equal(this.loanScale, other.loanScale)
                && Objects.equal(this.loanSacelOther, other.loanSacelOther)
                && Objects.equal(this.houseArea, other.houseArea)
                && Objects.equal(this.houseAreaOther, other.houseAreaOther)
                && Objects.equal(this.loanAmountTao, other.loanAmountTao)
                && Objects.equal(this.loanAmount, other.loanAmount)
                && Objects.equal(this.loanAmountOther, other.loanAmountOther)
                && Objects.equal(this.supportExtension, other.supportExtension)
                && Objects.equal(this.extensionFee, other.extensionFee)
                && Objects.equal(this.loanRate, other.loanRate)
                && Objects.equal(this.personMaterial, other.personMaterial)
                && Objects.equal(this.companyMaterial, other.companyMaterial)
                && Objects.equal(this.minAge, other.minAge)
                && Objects.equal(this.maxAge, other.maxAge)
                && Objects.equal(this.ageOther, other.ageOther)
                && Objects.equal(this.minMonth, other.minMonth)
                && Objects.equal(this.mixMonth, other.mixMonth)
                && Objects.equal(this.fixedMonth, other.fixedMonth)
                && Objects.equal(this.diyaType, other.diyaType)
                && Objects.equal(this.houseOwnership, other.houseOwnership)
                && Objects.equal(this.applyLoanType, other.applyLoanType)
                && Objects.equal(this.applyHouseAge, other.applyHouseAge)
                && Objects.equal(this.applyHouseNature, other.applyHouseNature)
                && Objects.equal(this.folkMortgageAffect, other.folkMortgageAffect)
                && Objects.equal(this.loanMaxHouseageplusloanyear, other.loanMaxHouseageplusloanyear)
                && Objects.equal(this.loanMaxHouseageplusloanyearOther, other.loanMaxHouseageplusloanyearOther)
                && Objects.equal(this.differentLoanMortgage, other.differentLoanMortgage)
                && Objects.equal(this.processDetails, other.processDetails)
                && Objects.equal(this.advantage, other.advantage)
                && Objects.equal(this.disadvantage, other.disadvantage)
                && Objects.equal(this.notice, other.notice)
                && Objects.equal(this.shootReason, other.shootReason)
                && Objects.equal(this.state, other.state)
                && Objects.equal(this.createTime, other.createTime)
                && Objects.equal(this.recommend, other.recommend)
                && Objects.equal(this.createPerson, other.createPerson);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("code", code)
                .add("agencyId", agencyId)
                .add("agencyName", agencyName)
                .add("loanPeople", loanPeople)
                .add("repaymentType", repaymentType)
                .add("loanBidType", loanBidType)
                .add("loanScale", loanScale)
                .add("loanSacelOther", loanSacelOther)
                .add("houseArea", houseArea)
                .add("houseAreaOther", houseAreaOther)
                .add("loanAmountTao", loanAmountTao)
                .add("loanAmount", loanAmount)
                .add("loanAmountOther", loanAmountOther)
                .add("supportExtension", supportExtension)
                .add("extensionFee", extensionFee)
                .add("loanRate", loanRate)
                .add("personMaterial", personMaterial)
                .add("companyMaterial", companyMaterial)
                .add("minAge", minAge)
                .add("maxAge", maxAge)
                .add("ageOther", ageOther)
                .add("minMonth", minMonth)
                .add("mixMonth", mixMonth)
                .add("fixedMonth", fixedMonth)
                .add("diyaType", diyaType)
                .add("houseOwnership", houseOwnership)
                .add("applyLoanType", applyLoanType)
                .add("applyHouseAge", applyHouseAge)
                .add("applyHouseNature", applyHouseNature)
                .add("folkMortgageAffect", folkMortgageAffect)
                .add("loanMaxHouseageplusloanyear", loanMaxHouseageplusloanyear)
                .add("loanMaxHouseageplusloanyearOther", loanMaxHouseageplusloanyearOther)
                .add("differentLoanMortgage", differentLoanMortgage)
                .add("processDetails", processDetails)
                .add("advantage", advantage)
                .add("disadvantage", disadvantage)
                .add("notice", notice)
                .add("shootReason", shootReason)
                .add("state", state)
                .add("createTime", createTime)
                .add("recommend", recommend)
                .add("createPerson", createPerson)
                .toString();
    }

    @Basic
    @Column(name = "log")
    public Integer getLog() {
        return log;
    }

    public void setLog(Integer log) {
        this.log = log;
    }
}

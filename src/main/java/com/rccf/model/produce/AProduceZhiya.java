package com.rccf.model.produce;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Date;
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
    private Date entryTime;

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

    @Basic
    @Column(name = "create_person")
    public Integer getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Integer createPerson) {
        this.createPerson = createPerson;
    }

    @Basic
    @Column(name = "log")
    public Integer getLog() {
        return log;
    }

    public void setLog(Integer log) {
        this.log = log;
    }

    @Basic
    @Column(name = "entry_time")
    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AProduceZhiya zhiya = (AProduceZhiya) o;
        return id == zhiya.id &&
                Objects.equal(name, zhiya.name) &&
                Objects.equal(code, zhiya.code) &&
                Objects.equal(agencyId, zhiya.agencyId) &&
                Objects.equal(agencyName, zhiya.agencyName) &&
                Objects.equal(loanPeople, zhiya.loanPeople) &&
                Objects.equal(repaymentType, zhiya.repaymentType) &&
                Objects.equal(loanBidType, zhiya.loanBidType) &&
                Objects.equal(loanScale, zhiya.loanScale) &&
                Objects.equal(loanSacelOther, zhiya.loanSacelOther) &&
                Objects.equal(houseArea, zhiya.houseArea) &&
                Objects.equal(houseAreaOther, zhiya.houseAreaOther) &&
                Objects.equal(loanAmountTao, zhiya.loanAmountTao) &&
                Objects.equal(loanAmount, zhiya.loanAmount) &&
                Objects.equal(loanAmountOther, zhiya.loanAmountOther) &&
                Objects.equal(supportExtension, zhiya.supportExtension) &&
                Objects.equal(extensionFee, zhiya.extensionFee) &&
                Objects.equal(loanRate, zhiya.loanRate) &&
                Objects.equal(personMaterial, zhiya.personMaterial) &&
                Objects.equal(companyMaterial, zhiya.companyMaterial) &&
                Objects.equal(minAge, zhiya.minAge) &&
                Objects.equal(maxAge, zhiya.maxAge) &&
                Objects.equal(ageOther, zhiya.ageOther) &&
                Objects.equal(minMonth, zhiya.minMonth) &&
                Objects.equal(mixMonth, zhiya.mixMonth) &&
                Objects.equal(fixedMonth, zhiya.fixedMonth) &&
                Objects.equal(diyaType, zhiya.diyaType) &&
                Objects.equal(houseOwnership, zhiya.houseOwnership) &&
                Objects.equal(applyLoanType, zhiya.applyLoanType) &&
                Objects.equal(applyHouseAge, zhiya.applyHouseAge) &&
                Objects.equal(applyHouseNature, zhiya.applyHouseNature) &&
                Objects.equal(folkMortgageAffect, zhiya.folkMortgageAffect) &&
                Objects.equal(loanMaxHouseageplusloanyear, zhiya.loanMaxHouseageplusloanyear) &&
                Objects.equal(loanMaxHouseageplusloanyearOther, zhiya.loanMaxHouseageplusloanyearOther) &&
                Objects.equal(differentLoanMortgage, zhiya.differentLoanMortgage) &&
                Objects.equal(processDetails, zhiya.processDetails) &&
                Objects.equal(advantage, zhiya.advantage) &&
                Objects.equal(disadvantage, zhiya.disadvantage) &&
                Objects.equal(notice, zhiya.notice) &&
                Objects.equal(shootReason, zhiya.shootReason) &&
                Objects.equal(state, zhiya.state) &&
                Objects.equal(createTime, zhiya.createTime) &&
                Objects.equal(recommend, zhiya.recommend) &&
                Objects.equal(createPerson, zhiya.createPerson) &&
                Objects.equal(log, zhiya.log) &&
                Objects.equal(entryTime, zhiya.entryTime);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, code, agencyId, agencyName, loanPeople, repaymentType, loanBidType, loanScale, loanSacelOther, houseArea, houseAreaOther, loanAmountTao, loanAmount, loanAmountOther, supportExtension, extensionFee, loanRate, personMaterial, companyMaterial, minAge, maxAge, ageOther, minMonth, mixMonth, fixedMonth, diyaType, houseOwnership, applyLoanType, applyHouseAge, applyHouseNature, folkMortgageAffect, loanMaxHouseageplusloanyear, loanMaxHouseageplusloanyearOther, differentLoanMortgage, processDetails, advantage, disadvantage, notice, shootReason, state, createTime, recommend, createPerson, log, entryTime);
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
                .add("log", log)
                .add("entryTime", entryTime)
                .toString();
    }
}

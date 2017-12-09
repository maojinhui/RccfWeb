package com.rccf.model.produce;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "a_produce_credit", schema = "rccf", catalog = "")
public class AProduceCredit {
    private int id;
    private Integer agencyId;
    private String agencyName;
    private String name;
    private String code;
    private String loanPeople;
    private String repaymentType;
    private Integer loanTimeMin;
    private Integer loanTimeMax;
    private Integer loanCreditType;
    private String loanArea;
    private Integer loanAmountMin;
    private Integer loanAmountMax;
    private Integer applyAgeMin;
    private Integer applyAgeMax;
    private String applyAgeDescription;
    private Double loanRateMin;
    private Double loanRateMax;
    private String loanRateDescription;
    private Integer loanTermMin;
    private Integer loanTermMax;
    private Integer loanShangkou;
    private String loanShagnkouDescription;
    private Integer loanWeiyuejin;
    private String loanWeiyuejinDescription;
    private Integer loanPingtaifei;
    private String loanPingtaifeiDescription;
    private String loanMaterialPersonal;
    private String loanMaterialCompany;
    private String loanAccess;
    private String creditInquireClaim;
    private String creditOverdueClaim;
    private String creditDebtClaim;
    private String creditOtherClaim;
    private String accessRequirement;
    private String processDetail;
    private String advantage;
    private String disadvantage;
    private String notice;
    private String shootReason;
    private Timestamp createTime;
    private Integer createPerson;
    private String recommend;
    private Integer state;
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
    @Column(name = "loan_time_min")
    public Integer getLoanTimeMin() {
        return loanTimeMin;
    }

    public void setLoanTimeMin(Integer loanTimeMin) {
        this.loanTimeMin = loanTimeMin;
    }

    @Basic
    @Column(name = "loan_time_max")
    public Integer getLoanTimeMax() {
        return loanTimeMax;
    }

    public void setLoanTimeMax(Integer loanTimeMax) {
        this.loanTimeMax = loanTimeMax;
    }

    @Basic
    @Column(name = "loan_credit_type")
    public Integer getLoanCreditType() {
        return loanCreditType;
    }

    public void setLoanCreditType(Integer loanCreditType) {
        this.loanCreditType = loanCreditType;
    }

    @Basic
    @Column(name = "loan_area")
    public String getLoanArea() {
        return loanArea;
    }

    public void setLoanArea(String loanArea) {
        this.loanArea = loanArea;
    }

    @Basic
    @Column(name = "loan_amount_min")
    public Integer getLoanAmountMin() {
        return loanAmountMin;
    }

    public void setLoanAmountMin(Integer loanAmountMin) {
        this.loanAmountMin = loanAmountMin;
    }

    @Basic
    @Column(name = "loan_amount_max")
    public Integer getLoanAmountMax() {
        return loanAmountMax;
    }

    public void setLoanAmountMax(Integer loanAmountMax) {
        this.loanAmountMax = loanAmountMax;
    }

    @Basic
    @Column(name = "apply_age_min")
    public Integer getApplyAgeMin() {
        return applyAgeMin;
    }

    public void setApplyAgeMin(Integer applyAgeMin) {
        this.applyAgeMin = applyAgeMin;
    }

    @Basic
    @Column(name = "apply_age_max")
    public Integer getApplyAgeMax() {
        return applyAgeMax;
    }

    public void setApplyAgeMax(Integer applyAgeMax) {
        this.applyAgeMax = applyAgeMax;
    }

    @Basic
    @Column(name = "apply_age_description")
    public String getApplyAgeDescription() {
        return applyAgeDescription;
    }

    public void setApplyAgeDescription(String applyAgeDescription) {
        this.applyAgeDescription = applyAgeDescription;
    }

    @Basic
    @Column(name = "loan_rate_min")
    public Double getLoanRateMin() {
        return loanRateMin;
    }

    public void setLoanRateMin(Double loanRateMin) {
        this.loanRateMin = loanRateMin;
    }

    @Basic
    @Column(name = "loan_rate_max")
    public Double getLoanRateMax() {
        return loanRateMax;
    }

    public void setLoanRateMax(Double loanRateMax) {
        this.loanRateMax = loanRateMax;
    }

    @Basic
    @Column(name = "loan_rate_description")
    public String getLoanRateDescription() {
        return loanRateDescription;
    }

    public void setLoanRateDescription(String loanRateDescription) {
        this.loanRateDescription = loanRateDescription;
    }

    @Basic
    @Column(name = "loan_term_min")
    public Integer getLoanTermMin() {
        return loanTermMin;
    }

    public void setLoanTermMin(Integer loanTermMin) {
        this.loanTermMin = loanTermMin;
    }

    @Basic
    @Column(name = "loan_term_max")
    public Integer getLoanTermMax() {
        return loanTermMax;
    }

    public void setLoanTermMax(Integer loanTermMax) {
        this.loanTermMax = loanTermMax;
    }

    @Basic
    @Column(name = "loan_shangkou")
    public Integer getLoanShangkou() {
        return loanShangkou;
    }

    public void setLoanShangkou(Integer loanShangkou) {
        this.loanShangkou = loanShangkou;
    }

    @Basic
    @Column(name = "loan_shagnkou_description")
    public String getLoanShagnkouDescription() {
        return loanShagnkouDescription;
    }

    public void setLoanShagnkouDescription(String loanShagnkouDescription) {
        this.loanShagnkouDescription = loanShagnkouDescription;
    }

    @Basic
    @Column(name = "loan_weiyuejin")
    public Integer getLoanWeiyuejin() {
        return loanWeiyuejin;
    }

    public void setLoanWeiyuejin(Integer loanWeiyuejin) {
        this.loanWeiyuejin = loanWeiyuejin;
    }

    @Basic
    @Column(name = "loan_weiyuejin_description")
    public String getLoanWeiyuejinDescription() {
        return loanWeiyuejinDescription;
    }

    public void setLoanWeiyuejinDescription(String loanWeiyuejinDescription) {
        this.loanWeiyuejinDescription = loanWeiyuejinDescription;
    }

    @Basic
    @Column(name = "loan_pingtaifei")
    public Integer getLoanPingtaifei() {
        return loanPingtaifei;
    }

    public void setLoanPingtaifei(Integer loanPingtaifei) {
        this.loanPingtaifei = loanPingtaifei;
    }

    @Basic
    @Column(name = "loan_pingtaifei_description")
    public String getLoanPingtaifeiDescription() {
        return loanPingtaifeiDescription;
    }

    public void setLoanPingtaifeiDescription(String loanPingtaifeiDescription) {
        this.loanPingtaifeiDescription = loanPingtaifeiDescription;
    }

    @Basic
    @Column(name = "loan_material_personal")
    public String getLoanMaterialPersonal() {
        return loanMaterialPersonal;
    }

    public void setLoanMaterialPersonal(String loanMaterialPersonal) {
        this.loanMaterialPersonal = loanMaterialPersonal;
    }

    @Basic
    @Column(name = "loan_material_company")
    public String getLoanMaterialCompany() {
        return loanMaterialCompany;
    }

    public void setLoanMaterialCompany(String loanMaterialCompany) {
        this.loanMaterialCompany = loanMaterialCompany;
    }

    @Basic
    @Column(name = "loan_access")
    public String getLoanAccess() {
        return loanAccess;
    }

    public void setLoanAccess(String loanAccess) {
        this.loanAccess = loanAccess;
    }

    @Basic
    @Column(name = "credit_inquire_claim")
    public String getCreditInquireClaim() {
        return creditInquireClaim;
    }

    public void setCreditInquireClaim(String creditInquireClaim) {
        this.creditInquireClaim = creditInquireClaim;
    }

    @Basic
    @Column(name = "credit_overdue_claim")
    public String getCreditOverdueClaim() {
        return creditOverdueClaim;
    }

    public void setCreditOverdueClaim(String creditOverdueClaim) {
        this.creditOverdueClaim = creditOverdueClaim;
    }

    @Basic
    @Column(name = "credit_debt_claim")
    public String getCreditDebtClaim() {
        return creditDebtClaim;
    }

    public void setCreditDebtClaim(String creditDebtClaim) {
        this.creditDebtClaim = creditDebtClaim;
    }

    @Basic
    @Column(name = "credit_other_claim")
    public String getCreditOtherClaim() {
        return creditOtherClaim;
    }

    public void setCreditOtherClaim(String creditOtherClaim) {
        this.creditOtherClaim = creditOtherClaim;
    }

    @Basic
    @Column(name = "access_requirement")
    public String getAccessRequirement() {
        return accessRequirement;
    }

    public void setAccessRequirement(String accessRequirement) {
        this.accessRequirement = accessRequirement;
    }

    @Basic
    @Column(name = "process_detail")
    public String getProcessDetail() {
        return processDetail;
    }

    public void setProcessDetail(String processDetail) {
        this.processDetail = processDetail;
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
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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
    @Column(name = "recommend")
    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
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
        AProduceCredit credit = (AProduceCredit) o;
        return id == credit.id &&
                Objects.equal(agencyId, credit.agencyId) &&
                Objects.equal(agencyName, credit.agencyName) &&
                Objects.equal(name, credit.name) &&
                Objects.equal(code, credit.code) &&
                Objects.equal(loanPeople, credit.loanPeople) &&
                Objects.equal(repaymentType, credit.repaymentType) &&
                Objects.equal(loanTimeMin, credit.loanTimeMin) &&
                Objects.equal(loanTimeMax, credit.loanTimeMax) &&
                Objects.equal(loanCreditType, credit.loanCreditType) &&
                Objects.equal(loanArea, credit.loanArea) &&
                Objects.equal(loanAmountMin, credit.loanAmountMin) &&
                Objects.equal(loanAmountMax, credit.loanAmountMax) &&
                Objects.equal(applyAgeMin, credit.applyAgeMin) &&
                Objects.equal(applyAgeMax, credit.applyAgeMax) &&
                Objects.equal(applyAgeDescription, credit.applyAgeDescription) &&
                Objects.equal(loanRateMin, credit.loanRateMin) &&
                Objects.equal(loanRateMax, credit.loanRateMax) &&
                Objects.equal(loanRateDescription, credit.loanRateDescription) &&
                Objects.equal(loanTermMin, credit.loanTermMin) &&
                Objects.equal(loanTermMax, credit.loanTermMax) &&
                Objects.equal(loanShangkou, credit.loanShangkou) &&
                Objects.equal(loanShagnkouDescription, credit.loanShagnkouDescription) &&
                Objects.equal(loanWeiyuejin, credit.loanWeiyuejin) &&
                Objects.equal(loanWeiyuejinDescription, credit.loanWeiyuejinDescription) &&
                Objects.equal(loanPingtaifei, credit.loanPingtaifei) &&
                Objects.equal(loanPingtaifeiDescription, credit.loanPingtaifeiDescription) &&
                Objects.equal(loanMaterialPersonal, credit.loanMaterialPersonal) &&
                Objects.equal(loanMaterialCompany, credit.loanMaterialCompany) &&
                Objects.equal(loanAccess, credit.loanAccess) &&
                Objects.equal(creditInquireClaim, credit.creditInquireClaim) &&
                Objects.equal(creditOverdueClaim, credit.creditOverdueClaim) &&
                Objects.equal(creditDebtClaim, credit.creditDebtClaim) &&
                Objects.equal(creditOtherClaim, credit.creditOtherClaim) &&
                Objects.equal(accessRequirement, credit.accessRequirement) &&
                Objects.equal(processDetail, credit.processDetail) &&
                Objects.equal(advantage, credit.advantage) &&
                Objects.equal(disadvantage, credit.disadvantage) &&
                Objects.equal(notice, credit.notice) &&
                Objects.equal(shootReason, credit.shootReason) &&
                Objects.equal(createTime, credit.createTime) &&
                Objects.equal(createPerson, credit.createPerson) &&
                Objects.equal(recommend, credit.recommend) &&
                Objects.equal(state, credit.state) &&
                Objects.equal(log, credit.log) &&
                Objects.equal(entryTime, credit.entryTime);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, agencyId, agencyName, name, code, loanPeople, repaymentType, loanTimeMin, loanTimeMax, loanCreditType, loanArea, loanAmountMin, loanAmountMax, applyAgeMin, applyAgeMax, applyAgeDescription, loanRateMin, loanRateMax, loanRateDescription, loanTermMin, loanTermMax, loanShangkou, loanShagnkouDescription, loanWeiyuejin, loanWeiyuejinDescription, loanPingtaifei, loanPingtaifeiDescription, loanMaterialPersonal, loanMaterialCompany, loanAccess, creditInquireClaim, creditOverdueClaim, creditDebtClaim, creditOtherClaim, accessRequirement, processDetail, advantage, disadvantage, notice, shootReason, createTime, createPerson, recommend, state, log, entryTime);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("agencyId", agencyId)
                .add("agencyName", agencyName)
                .add("name", name)
                .add("code", code)
                .add("loanPeople", loanPeople)
                .add("repaymentType", repaymentType)
                .add("loanTimeMin", loanTimeMin)
                .add("loanTimeMax", loanTimeMax)
                .add("loanCreditType", loanCreditType)
                .add("loanArea", loanArea)
                .add("loanAmountMin", loanAmountMin)
                .add("loanAmountMax", loanAmountMax)
                .add("applyAgeMin", applyAgeMin)
                .add("applyAgeMax", applyAgeMax)
                .add("applyAgeDescription", applyAgeDescription)
                .add("loanRateMin", loanRateMin)
                .add("loanRateMax", loanRateMax)
                .add("loanRateDescription", loanRateDescription)
                .add("loanTermMin", loanTermMin)
                .add("loanTermMax", loanTermMax)
                .add("loanShangkou", loanShangkou)
                .add("loanShagnkouDescription", loanShagnkouDescription)
                .add("loanWeiyuejin", loanWeiyuejin)
                .add("loanWeiyuejinDescription", loanWeiyuejinDescription)
                .add("loanPingtaifei", loanPingtaifei)
                .add("loanPingtaifeiDescription", loanPingtaifeiDescription)
                .add("loanMaterialPersonal", loanMaterialPersonal)
                .add("loanMaterialCompany", loanMaterialCompany)
                .add("loanAccess", loanAccess)
                .add("creditInquireClaim", creditInquireClaim)
                .add("creditOverdueClaim", creditOverdueClaim)
                .add("creditDebtClaim", creditDebtClaim)
                .add("creditOtherClaim", creditOtherClaim)
                .add("accessRequirement", accessRequirement)
                .add("processDetail", processDetail)
                .add("advantage", advantage)
                .add("disadvantage", disadvantage)
                .add("notice", notice)
                .add("shootReason", shootReason)
                .add("createTime", createTime)
                .add("createPerson", createPerson)
                .add("recommend", recommend)
                .add("state", state)
                .add("log", log)
                .add("entryTime", entryTime)
                .toString();
    }
}

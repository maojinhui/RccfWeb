package com.rccf.model.produce;

import com.google.common.base.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "a_produce_credit", schema = "rccf", catalog = "")
public class AProduceCredit {
    private Integer id;
    private Integer agegncyId;
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

    @Basic
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "agegncy_id")
    public Integer getAgegncyId() {
        return agegncyId;
    }

    public void setAgegncyId(Integer agegncyId) {
        this.agegncyId = agegncyId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AProduceCredit that = (AProduceCredit) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(agegncyId, that.agegncyId) &&
                Objects.equal(agencyName, that.agencyName) &&
                Objects.equal(name, that.name) &&
                Objects.equal(code, that.code) &&
                Objects.equal(loanPeople, that.loanPeople) &&
                Objects.equal(repaymentType, that.repaymentType) &&
                Objects.equal(loanTimeMin, that.loanTimeMin) &&
                Objects.equal(loanTimeMax, that.loanTimeMax) &&
                Objects.equal(loanCreditType, that.loanCreditType) &&
                Objects.equal(loanArea, that.loanArea) &&
                Objects.equal(loanAmountMin, that.loanAmountMin) &&
                Objects.equal(loanAmountMax, that.loanAmountMax) &&
                Objects.equal(applyAgeMin, that.applyAgeMin) &&
                Objects.equal(applyAgeMax, that.applyAgeMax) &&
                Objects.equal(applyAgeDescription, that.applyAgeDescription) &&
                Objects.equal(loanRateMin, that.loanRateMin) &&
                Objects.equal(loanRateMax, that.loanRateMax) &&
                Objects.equal(loanRateDescription, that.loanRateDescription) &&
                Objects.equal(loanTermMin, that.loanTermMin) &&
                Objects.equal(loanTermMax, that.loanTermMax) &&
                Objects.equal(loanShangkou, that.loanShangkou) &&
                Objects.equal(loanShagnkouDescription, that.loanShagnkouDescription) &&
                Objects.equal(loanWeiyuejin, that.loanWeiyuejin) &&
                Objects.equal(loanWeiyuejinDescription, that.loanWeiyuejinDescription) &&
                Objects.equal(loanPingtaifei, that.loanPingtaifei) &&
                Objects.equal(loanPingtaifeiDescription, that.loanPingtaifeiDescription) &&
                Objects.equal(loanMaterialPersonal, that.loanMaterialPersonal) &&
                Objects.equal(loanMaterialCompany, that.loanMaterialCompany) &&
                Objects.equal(creditInquireClaim, that.creditInquireClaim) &&
                Objects.equal(creditOverdueClaim, that.creditOverdueClaim) &&
                Objects.equal(creditDebtClaim, that.creditDebtClaim) &&
                Objects.equal(creditOtherClaim, that.creditOtherClaim) &&
                Objects.equal(accessRequirement, that.accessRequirement) &&
                Objects.equal(processDetail, that.processDetail) &&
                Objects.equal(advantage, that.advantage) &&
                Objects.equal(disadvantage, that.disadvantage) &&
                Objects.equal(notice, that.notice) &&
                Objects.equal(shootReason, that.shootReason) &&
                Objects.equal(createTime, that.createTime) &&
                Objects.equal(createPerson, that.createPerson) &&
                Objects.equal(recommend, that.recommend) &&
                Objects.equal(state, that.state) &&
                Objects.equal(log, that.log);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, agegncyId, agencyName, name, code, loanPeople, repaymentType, loanTimeMin, loanTimeMax, loanCreditType, loanArea, loanAmountMin, loanAmountMax, applyAgeMin, applyAgeMax, applyAgeDescription, loanRateMin, loanRateMax, loanRateDescription, loanTermMin, loanTermMax, loanShangkou, loanShagnkouDescription, loanWeiyuejin, loanWeiyuejinDescription, loanPingtaifei, loanPingtaifeiDescription, loanMaterialPersonal, loanMaterialCompany, creditInquireClaim, creditOverdueClaim, creditDebtClaim, creditOtherClaim, accessRequirement, processDetail, advantage, disadvantage, notice, shootReason, createTime, createPerson, recommend, state, log);
    }
}

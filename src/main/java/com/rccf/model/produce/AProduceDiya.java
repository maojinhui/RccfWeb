package com.rccf.model.produce;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Timestamp;

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
    private Timestamp createTime;

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
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (agencyName != null ? agencyName.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AProduceDiya)) return false;

        AProduceDiya that = (AProduceDiya) o;

        if (getId() != that.getId()) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getCode() != null ? !getCode().equals(that.getCode()) : that.getCode() != null) return false;
        if (getAgencyId() != null ? !getAgencyId().equals(that.getAgencyId()) : that.getAgencyId() != null)
            return false;
        if (getLoanPeople() != null ? !getLoanPeople().equals(that.getLoanPeople()) : that.getLoanPeople() != null)
            return false;
        if (getRepaymentType() != null ? !getRepaymentType().equals(that.getRepaymentType()) : that.getRepaymentType() != null)
            return false;
        if (getLoanBidType() != null ? !getLoanBidType().equals(that.getLoanBidType()) : that.getLoanBidType() != null)
            return false;
        if (getLoanScale() != null ? !getLoanScale().equals(that.getLoanScale()) : that.getLoanScale() != null)
            return false;
        if (getLoanSacelOther() != null ? !getLoanSacelOther().equals(that.getLoanSacelOther()) : that.getLoanSacelOther() != null)
            return false;
        if (getHouseArea() != null ? !getHouseArea().equals(that.getHouseArea()) : that.getHouseArea() != null)
            return false;
        if (getHouseAreaOther() != null ? !getHouseAreaOther().equals(that.getHouseAreaOther()) : that.getHouseAreaOther() != null)
            return false;
        if (getLoanAmountTao() != null ? !getLoanAmountTao().equals(that.getLoanAmountTao()) : that.getLoanAmountTao() != null)
            return false;
        if (getLoanAmount() != null ? !getLoanAmount().equals(that.getLoanAmount()) : that.getLoanAmount() != null)
            return false;
        if (getLoanAmountOther() != null ? !getLoanAmountOther().equals(that.getLoanAmountOther()) : that.getLoanAmountOther() != null)
            return false;
        if (getLoanRate() != null ? !getLoanRate().equals(that.getLoanRate()) : that.getLoanRate() != null)
            return false;
        if (getLoanRateOther() != null ? !getLoanRateOther().equals(that.getLoanRateOther()) : that.getLoanRateOther() != null)
            return false;
        if (getPersonMaterial() != null ? !getPersonMaterial().equals(that.getPersonMaterial()) : that.getPersonMaterial() != null)
            return false;
        if (getCompanyMaterial() != null ? !getCompanyMaterial().equals(that.getCompanyMaterial()) : that.getCompanyMaterial() != null)
            return false;
        if (getMinAge() != null ? !getMinAge().equals(that.getMinAge()) : that.getMinAge() != null) return false;
        if (getMaxAge() != null ? !getMaxAge().equals(that.getMaxAge()) : that.getMaxAge() != null) return false;
        if (getAgeOther() != null ? !getAgeOther().equals(that.getAgeOther()) : that.getAgeOther() != null)
            return false;
        if (getMinMonth() != null ? !getMinMonth().equals(that.getMinMonth()) : that.getMinMonth() != null)
            return false;
        if (getMixMonth() != null ? !getMixMonth().equals(that.getMixMonth()) : that.getMixMonth() != null)
            return false;
        if (getFixedMonth() != null ? !getFixedMonth().equals(that.getFixedMonth()) : that.getFixedMonth() != null)
            return false;
        if (getDiyaType() != null ? !getDiyaType().equals(that.getDiyaType()) : that.getDiyaType() != null)
            return false;
        if (getHouseOwnership() != null ? !getHouseOwnership().equals(that.getHouseOwnership()) : that.getHouseOwnership() != null)
            return false;
        if (getApplyLoanType() != null ? !getApplyLoanType().equals(that.getApplyLoanType()) : that.getApplyLoanType() != null)
            return false;
        if (getApplyHouseAge() != null ? !getApplyHouseAge().equals(that.getApplyHouseAge()) : that.getApplyHouseAge() != null)
            return false;
        if (getApplyHouseNature() != null ? !getApplyHouseNature().equals(that.getApplyHouseNature()) : that.getApplyHouseNature() != null)
            return false;
        if (getFolkMortgageAffect() != null ? !getFolkMortgageAffect().equals(that.getFolkMortgageAffect()) : that.getFolkMortgageAffect() != null)
            return false;
        if (getLoanMaxHouseageplusloanyear() != null ? !getLoanMaxHouseageplusloanyear().equals(that.getLoanMaxHouseageplusloanyear()) : that.getLoanMaxHouseageplusloanyear() != null)
            return false;
        if (getLoanMaxHouseageplusloanyearOther() != null ? !getLoanMaxHouseageplusloanyearOther().equals(that.getLoanMaxHouseageplusloanyearOther()) : that.getLoanMaxHouseageplusloanyearOther() != null)
            return false;
        if (getDifferentLoanMortgage() != null ? !getDifferentLoanMortgage().equals(that.getDifferentLoanMortgage()) : that.getDifferentLoanMortgage() != null)
            return false;
        if (getProcessDetails() != null ? !getProcessDetails().equals(that.getProcessDetails()) : that.getProcessDetails() != null)
            return false;
        if (getAdvantage() != null ? !getAdvantage().equals(that.getAdvantage()) : that.getAdvantage() != null)
            return false;
        if (getDisadvantage() != null ? !getDisadvantage().equals(that.getDisadvantage()) : that.getDisadvantage() != null)
            return false;
        if (getNotice() != null ? !getNotice().equals(that.getNotice()) : that.getNotice() != null) return false;
        if (getShootReason() != null ? !getShootReason().equals(that.getShootReason()) : that.getShootReason() != null)
            return false;
        if (getState() != null ? !getState().equals(that.getState()) : that.getState() != null) return false;
        return getAgencyName() != null ? getAgencyName().equals(that.getAgencyName()) : that.getAgencyName() == null;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("code", code)
                .add("agencyId", agencyId)
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
                .add("loanRate", loanRate)
                .add("loanRateOther", loanRateOther)
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
                .add("agencyName", agencyName)
                .toString();
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}

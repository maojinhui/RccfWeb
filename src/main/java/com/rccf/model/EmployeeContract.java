package com.rccf.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "employee_contract", schema = "rccf", catalog = "")
public class EmployeeContract {
    private int eid;
    private String workType;
    private String contractCode;
    private Date contractReleaseDate;
    private String contractType;
    private String contractDeadline;
    private Date contractEntryTime;
    private Date contractSignDate;
    private Date contractEffectDate;
    private Date contractTurnDate;
    private Date contractEndDate;
    private Integer contractLaveDay;
    private String contractChange;
    private String contractContinue;

    @Id
    @Column(name = "eid")
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    @Basic
    @Column(name = "work_type")
    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    @Basic
    @Column(name = "contract_code")
    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    @Basic
    @Column(name = "contract_release_date")
    public Date getContractReleaseDate() {
        return contractReleaseDate;
    }

    public void setContractReleaseDate(Date contractReleaseDate) {
        this.contractReleaseDate = contractReleaseDate;
    }

    @Basic
    @Column(name = "contract_type")
    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    @Basic
    @Column(name = "contract_deadline")
    public String getContractDeadline() {
        return contractDeadline;
    }

    public void setContractDeadline(String contractDeadline) {
        this.contractDeadline = contractDeadline;
    }

    @Basic
    @Column(name = "contract_entry_time")
    public Date getContractEntryTime() {
        return contractEntryTime;
    }

    public void setContractEntryTime(Date contractEntryTime) {
        this.contractEntryTime = contractEntryTime;
    }

    @Basic
    @Column(name = "contract_sign_date")
    public Date getContractSignDate() {
        return contractSignDate;
    }

    public void setContractSignDate(Date contractSignDate) {
        this.contractSignDate = contractSignDate;
    }

    @Basic
    @Column(name = "contract_effect_date")
    public Date getContractEffectDate() {
        return contractEffectDate;
    }

    public void setContractEffectDate(Date contractEffectDate) {
        this.contractEffectDate = contractEffectDate;
    }

    @Basic
    @Column(name = "contract_turn_date")
    public Date getContractTurnDate() {
        return contractTurnDate;
    }

    public void setContractTurnDate(Date contractTurnDate) {
        this.contractTurnDate = contractTurnDate;
    }

    @Basic
    @Column(name = "contract_end_date")
    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    @Basic
    @Column(name = "contract_lave_day")
    public Integer getContractLaveDay() {
        return contractLaveDay;
    }

    public void setContractLaveDay(Integer contractLaveDay) {
        this.contractLaveDay = contractLaveDay;
    }

    @Basic
    @Column(name = "contract_change")
    public String getContractChange() {
        return contractChange;
    }

    public void setContractChange(String contractChange) {
        this.contractChange = contractChange;
    }

    @Basic
    @Column(name = "contract_continue")
    public String getContractContinue() {
        return contractContinue;
    }

    public void setContractContinue(String contractContinue) {
        this.contractContinue = contractContinue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeContract contract = (EmployeeContract) o;

        if (eid != contract.eid) return false;
        if (workType != null ? !workType.equals(contract.workType) : contract.workType != null) return false;
        if (contractCode != null ? !contractCode.equals(contract.contractCode) : contract.contractCode != null)
            return false;
        if (contractReleaseDate != null ? !contractReleaseDate.equals(contract.contractReleaseDate) : contract.contractReleaseDate != null)
            return false;
        if (contractType != null ? !contractType.equals(contract.contractType) : contract.contractType != null)
            return false;
        if (contractDeadline != null ? !contractDeadline.equals(contract.contractDeadline) : contract.contractDeadline != null)
            return false;
        if (contractEntryTime != null ? !contractEntryTime.equals(contract.contractEntryTime) : contract.contractEntryTime != null)
            return false;
        if (contractSignDate != null ? !contractSignDate.equals(contract.contractSignDate) : contract.contractSignDate != null)
            return false;
        if (contractEffectDate != null ? !contractEffectDate.equals(contract.contractEffectDate) : contract.contractEffectDate != null)
            return false;
        if (contractTurnDate != null ? !contractTurnDate.equals(contract.contractTurnDate) : contract.contractTurnDate != null)
            return false;
        if (contractEndDate != null ? !contractEndDate.equals(contract.contractEndDate) : contract.contractEndDate != null)
            return false;
        if (contractLaveDay != null ? !contractLaveDay.equals(contract.contractLaveDay) : contract.contractLaveDay != null)
            return false;
        if (contractChange != null ? !contractChange.equals(contract.contractChange) : contract.contractChange != null)
            return false;
        if (contractContinue != null ? !contractContinue.equals(contract.contractContinue) : contract.contractContinue != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eid;
        result = 31 * result + (workType != null ? workType.hashCode() : 0);
        result = 31 * result + (contractCode != null ? contractCode.hashCode() : 0);
        result = 31 * result + (contractReleaseDate != null ? contractReleaseDate.hashCode() : 0);
        result = 31 * result + (contractType != null ? contractType.hashCode() : 0);
        result = 31 * result + (contractDeadline != null ? contractDeadline.hashCode() : 0);
        result = 31 * result + (contractEntryTime != null ? contractEntryTime.hashCode() : 0);
        result = 31 * result + (contractSignDate != null ? contractSignDate.hashCode() : 0);
        result = 31 * result + (contractEffectDate != null ? contractEffectDate.hashCode() : 0);
        result = 31 * result + (contractTurnDate != null ? contractTurnDate.hashCode() : 0);
        result = 31 * result + (contractEndDate != null ? contractEndDate.hashCode() : 0);
        result = 31 * result + (contractLaveDay != null ? contractLaveDay.hashCode() : 0);
        result = 31 * result + (contractChange != null ? contractChange.hashCode() : 0);
        result = 31 * result + (contractContinue != null ? contractContinue.hashCode() : 0);
        return result;
    }
}

package com.rccf.model;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "accept_income_expenditure_detail", schema = "rccf", catalog = "")
public class AcceptIncomeExpenditureDetail {
    private int id;
    private Integer incomeExpenditureId;
    private String oldStr;
    private String newStr;
    private Integer admin;
    private Timestamp adminTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "income_expenditure_id")
    public Integer getIncomeExpenditureId() {
        return incomeExpenditureId;
    }

    public void setIncomeExpenditureId(Integer incomeExpenditureId) {
        this.incomeExpenditureId = incomeExpenditureId;
    }

    @Basic
    @Column(name = "oldStr")
    public String getOldStr() {
        return oldStr;
    }

    public void setOldStr(String oldStr) {
        this.oldStr = oldStr;
    }

    @Basic
    @Column(name = "newStr")
    public String getNewStr() {
        return newStr;
    }

    public void setNewStr(String newStr) {
        this.newStr = newStr;
    }

    @Basic
    @Column(name = "admin")
    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    @Basic
    @Column(name = "admin_time")
    public Timestamp getAdminTime() {
        return adminTime;
    }

    public void setAdminTime(Timestamp adminTime) {
        this.adminTime = adminTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcceptIncomeExpenditureDetail detail = (AcceptIncomeExpenditureDetail) o;
        return id == detail.id &&
                Objects.equal(incomeExpenditureId, detail.incomeExpenditureId) &&
                Objects.equal(oldStr, detail.oldStr) &&
                Objects.equal(newStr, detail.newStr) &&
                Objects.equal(admin, detail.admin) &&
                Objects.equal(adminTime, detail.adminTime);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, incomeExpenditureId, oldStr, newStr, admin, adminTime);
    }
}

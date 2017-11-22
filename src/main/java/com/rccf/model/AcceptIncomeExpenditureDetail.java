package com.rccf.model;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "accept_income_expenditure_detail", schema = "rccf", catalog = "")
public class AcceptIncomeExpenditureDetail {
    private int id;
    private Integer acceptId;
    private String oldStr;
    private String newStr;
    private Integer admin;
    private Timestamp adminTime;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "accept_id")
    public Integer getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(Integer acceptId) {
        this.acceptId = acceptId;
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
        AcceptIncomeExpenditureDetail that = (AcceptIncomeExpenditureDetail) o;
        return id == that.id &&
                Objects.equal(acceptId, that.acceptId) &&
                Objects.equal(oldStr, that.oldStr) &&
                Objects.equal(newStr, that.newStr) &&
                Objects.equal(admin, that.admin) &&
                Objects.equal(adminTime, that.adminTime);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, acceptId, oldStr, newStr, admin, adminTime);
    }
}

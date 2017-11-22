package com.rccf.model;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "accept_income_expenditure", schema = "rccf", catalog = "")
public class AcceptIncomeExpenditure {
    private int id;
    private Integer acceptId;
    private Integer type;
    private String person;
    private Integer subjectId;
    private String subject;
    private Double amount;
    private String description;
    private Timestamp dealTime;
    private Integer admin;
    private Timestamp createTime;
    private String othersideName;
    private String othersideAccount;
    private String transCode;

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
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "person")
    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    @Basic
    @Column(name = "subject_id")
    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @Basic
    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "amount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "deal_time")
    public Timestamp getDealTime() {
        return dealTime;
    }

    public void setDealTime(Timestamp dealTime) {
        this.dealTime = dealTime;
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
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "otherside_name")
    public String getOthersideName() {
        return othersideName;
    }

    public void setOthersideName(String othersideName) {
        this.othersideName = othersideName;
    }

    @Basic
    @Column(name = "otherside_account")
    public String getOthersideAccount() {
        return othersideAccount;
    }

    public void setOthersideAccount(String othersideAccount) {
        this.othersideAccount = othersideAccount;
    }

    @Basic
    @Column(name = "trans_code")
    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcceptIncomeExpenditure that = (AcceptIncomeExpenditure) o;
        return id == that.id &&
                Objects.equal(acceptId, that.acceptId) &&
                Objects.equal(type, that.type) &&
                Objects.equal(person, that.person) &&
                Objects.equal(subjectId, that.subjectId) &&
                Objects.equal(subject, that.subject) &&
                Objects.equal(amount, that.amount) &&
                Objects.equal(description, that.description) &&
                Objects.equal(dealTime, that.dealTime) &&
                Objects.equal(admin, that.admin) &&
                Objects.equal(createTime, that.createTime) &&
                Objects.equal(othersideName, that.othersideName) &&
                Objects.equal(othersideAccount, that.othersideAccount) &&
                Objects.equal(transCode, that.transCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, acceptId, type, person, subjectId, subject, amount, description, dealTime, admin, createTime, othersideName, othersideAccount, transCode);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("acceptId", acceptId)
                .add("type", type)
                .add("person", person)
                .add("subjectId", subjectId)
                .add("subject", subject)
                .add("amount", amount)
                .add("description", description)
                .add("dealTime", dealTime)
                .add("admin", admin)
                .add("createTime", createTime)
                .add("othersideName", othersideName)
                .add("othersideAccount", othersideAccount)
                .add("transCode", transCode)
                .toString();
    }
}

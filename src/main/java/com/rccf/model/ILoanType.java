package com.rccf.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "i_loan_type", schema = "rccf", catalog = "")
public class ILoanType {
    private int id;
    private String name;
    private String decription;
    private Timestamp createTime;
    private Integer createPerson;

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
    @Column(name = "decription")
    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ILoanType iLoanType = (ILoanType) o;

        if (id != iLoanType.id) return false;
        if (name != null ? !name.equals(iLoanType.name) : iLoanType.name != null) return false;
        if (decription != null ? !decription.equals(iLoanType.decription) : iLoanType.decription != null) return false;
        if (createTime != null ? !createTime.equals(iLoanType.createTime) : iLoanType.createTime != null) return false;
        if (createPerson != null ? !createPerson.equals(iLoanType.createPerson) : iLoanType.createPerson != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (decription != null ? decription.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createPerson != null ? createPerson.hashCode() : 0);
        return result;
    }
}

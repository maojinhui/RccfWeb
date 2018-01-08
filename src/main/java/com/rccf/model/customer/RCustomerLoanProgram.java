package com.rccf.model.customer;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "r_customer_loan_program", schema = "rccf", catalog = "")
public class RCustomerLoanProgram {
    private int programId;
    private Integer submitPerson;
    private String customerId;
    private String products;
    private Timestamp createTime;
    private Integer createPerson;
    private Integer state;

    @Id
    @Column(name = "program_id", nullable = false)
    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    @Basic
    @Column(name = "submit_person", nullable = true)
    public Integer getSubmitPerson() {
        return submitPerson;
    }

    public void setSubmitPerson(Integer submitPerson) {
        this.submitPerson = submitPerson;
    }

    @Basic
    @Column(name = "customer_id", nullable = true, length = 32)
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "products", nullable = true, length = -1)
    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "create_person", nullable = true)
    public Integer getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Integer createPerson) {
        this.createPerson = createPerson;
    }

    @Basic
    @Column(name = "state", nullable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RCustomerLoanProgram that = (RCustomerLoanProgram) o;
        return programId == that.programId &&
                Objects.equal(submitPerson, that.submitPerson) &&
                Objects.equal(customerId, that.customerId) &&
                Objects.equal(products, that.products) &&
                Objects.equal(createTime, that.createTime) &&
                Objects.equal(createPerson, that.createPerson) &&
                Objects.equal(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(programId, submitPerson, customerId, products, createTime, createPerson, state);
    }
}

package com.rccf.model.customer;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "r_customer_file_del", schema = "rccf", catalog = "")
public class RCustomerFileDel {
    private int id;
    private String customerId;
    private Integer type;
    private String file;
    private Timestamp delTime;
    private Integer delPerson;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "file", nullable = true, length = 255)
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Basic
    @Column(name = "del_time", nullable = true)
    public Timestamp getDelTime() {
        return delTime;
    }

    public void setDelTime(Timestamp delTime) {
        this.delTime = delTime;
    }

    @Basic
    @Column(name = "del_person", nullable = true)
    public Integer getDelPerson() {
        return delPerson;
    }

    public void setDelPerson(Integer delPerson) {
        this.delPerson = delPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RCustomerFileDel that = (RCustomerFileDel) o;
        return id == that.id &&
                Objects.equal(customerId, that.customerId) &&
                Objects.equal(type, that.type) &&
                Objects.equal(file, that.file) &&
                Objects.equal(delTime, that.delTime) &&
                Objects.equal(delPerson, that.delPerson);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, customerId, type, file, delTime, delPerson);
    }
}

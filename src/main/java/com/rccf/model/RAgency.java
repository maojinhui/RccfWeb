package com.rccf.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "r_agency", schema = "rccf", catalog = "")
public class RAgency {
    private int id;
    private String code;
    private String name;
    private Timestamp createTime;
    private Integer createPerson;
    private Timestamp updateTime;
    private Integer updatePerson;
    private Integer state;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "update_person")
    public Integer getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(Integer updatePerson) {
        this.updatePerson = updatePerson;
    }

    @Basic
    @Column(name = "state")
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

        RAgency rAgency = (RAgency) o;

        if (id != rAgency.id) return false;
        if (code != null ? !code.equals(rAgency.code) : rAgency.code != null) return false;
        if (name != null ? !name.equals(rAgency.name) : rAgency.name != null) return false;
        if (createTime != null ? !createTime.equals(rAgency.createTime) : rAgency.createTime != null) return false;
        if (createPerson != null ? !createPerson.equals(rAgency.createPerson) : rAgency.createPerson != null)
            return false;
        if (updateTime != null ? !updateTime.equals(rAgency.updateTime) : rAgency.updateTime != null) return false;
        if (updatePerson != null ? !updatePerson.equals(rAgency.updatePerson) : rAgency.updatePerson != null)
            return false;
        if (state != null ? !state.equals(rAgency.state) : rAgency.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createPerson != null ? createPerson.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (updatePerson != null ? updatePerson.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}

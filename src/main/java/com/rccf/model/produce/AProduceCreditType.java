package com.rccf.model.produce;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "a_produce_credit_type", schema = "rccf", catalog = "")
public class AProduceCreditType {
    private int id;
    private String name;
    private Timestamp addTime;
    private Integer addPerson;
    private String description;

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
    @Column(name = "add_time")
    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    @Basic
    @Column(name = "add_person")
    public Integer getAddPerson() {
        return addPerson;
    }

    public void setAddPerson(Integer addPerson) {
        this.addPerson = addPerson;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AProduceCreditType that = (AProduceCreditType) o;
        return id == that.id &&
                Objects.equal(name, that.name) &&
                Objects.equal(addTime, that.addTime) &&
                Objects.equal(addPerson, that.addPerson) &&
                Objects.equal(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, addTime, addPerson, description);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("addTime", addTime)
                .add("addPerson", addPerson)
                .add("description", description)
                .toString();
    }
}

package com.rccf.model.produce;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
@Table(name = "produce_repayment", schema = "rccf", catalog = "")
public class ProduceRepayment {
    private Integer id;
    private String name;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduceRepayment that = (ProduceRepayment) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name);
    }
}

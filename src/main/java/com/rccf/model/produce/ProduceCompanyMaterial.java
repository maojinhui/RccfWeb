package com.rccf.model.produce;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
@Table(name = "produce_company_material", schema = "rccf", catalog = "")
public class ProduceCompanyMaterial {
    private int id;
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduceCompanyMaterial that = (ProduceCompanyMaterial) o;
        return id == that.id &&
                Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name);
    }
}

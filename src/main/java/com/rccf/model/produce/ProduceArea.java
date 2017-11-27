package com.rccf.model.produce;

import javax.persistence.*;

@Entity
@Table(name = "produce_area", schema = "rccf", catalog = "")
public class ProduceArea {
    private int areaId;
    private String areaName;

    @Id
    @Column(name = "area_id")
    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    @Basic
    @Column(name = "area_name")
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProduceArea that = (ProduceArea) o;

        if (areaId != that.areaId) return false;
        if (areaName != null ? !areaName.equals(that.areaName) : that.areaName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = areaId;
        result = 31 * result + (areaName != null ? areaName.hashCode() : 0);
        return result;
    }
}

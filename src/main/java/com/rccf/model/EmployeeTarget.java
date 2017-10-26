package com.rccf.model;

import javax.persistence.*;

@Entity
@Table(name = "employee_target", schema = "rccf", catalog = "")
public class EmployeeTarget {
    private int eid;
    private Double target;

    @Id
    @Column(name = "eid")
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    @Basic
    @Column(name = "target")
    public Double getTarget() {
        return target;
    }

    public void setTarget(Double target) {
        this.target = target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeTarget that = (EmployeeTarget) o;

        if (eid != that.eid) return false;
        if (target != null ? !target.equals(that.target) : that.target != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eid;
        result = 31 * result + (target != null ? target.hashCode() : 0);
        return result;
    }
}

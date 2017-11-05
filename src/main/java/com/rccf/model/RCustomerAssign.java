package com.rccf.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "r_customer_assign", schema = "rccf", catalog = "")
public class RCustomerAssign {
    private String customerId;
    private Integer director;
    private Integer deputyDirector;
    private Integer salesman;
    private Integer assign;
    private Integer admin;
    private Timestamp adminTime;

    @Id
    @Column(name = "customer_id")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "director")
    public Integer getDirector() {
        return director;
    }

    public void setDirector(Integer director) {
        this.director = director;
    }

    @Basic
    @Column(name = "deputy_director")
    public Integer getDeputyDirector() {
        return deputyDirector;
    }

    public void setDeputyDirector(Integer deputyDirector) {
        this.deputyDirector = deputyDirector;
    }

    @Basic
    @Column(name = "salesman")
    public Integer getSalesman() {
        return salesman;
    }

    public void setSalesman(Integer salesman) {
        this.salesman = salesman;
    }

    @Basic
    @Column(name = "assign")
    public Integer getAssign() {
        return assign;
    }

    public void setAssign(Integer assign) {
        this.assign = assign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RCustomerAssign that = (RCustomerAssign) o;

        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (director != null ? !director.equals(that.director) : that.director != null) return false;
        if (deputyDirector != null ? !deputyDirector.equals(that.deputyDirector) : that.deputyDirector != null)
            return false;
        if (salesman != null ? !salesman.equals(that.salesman) : that.salesman != null) return false;
        if (assign != null ? !assign.equals(that.assign) : that.assign != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerId != null ? customerId.hashCode() : 0;
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (deputyDirector != null ? deputyDirector.hashCode() : 0);
        result = 31 * result + (salesman != null ? salesman.hashCode() : 0);
        result = 31 * result + (assign != null ? assign.hashCode() : 0);
        return result;
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
    @Column(name = "admin_time")
    public Timestamp getAdminTime() {
        return adminTime;
    }

    public void setAdminTime(Timestamp adminTime) {
        this.adminTime = adminTime;
    }
}

package com.rccf.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_apply", schema = "rccf", catalog = "")
public class UserApply {
    private int id;
    private String userId;
    private String phone;
    private String type;
    private Timestamp applyTime;
    private Timestamp dealTime;
    private Integer assign;
    private Integer admin;
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
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "apply_time")
    public Timestamp getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Timestamp applyTime) {
        this.applyTime = applyTime;
    }

    @Basic
    @Column(name = "deal_time")
    public Timestamp getDealTime() {
        return dealTime;
    }

    public void setDealTime(Timestamp dealTime) {
        this.dealTime = dealTime;
    }

    @Basic
    @Column(name = "assign")
    public Integer getAssign() {
        return assign;
    }

    public void setAssign(Integer assign) {
        this.assign = assign;
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

        UserApply userApply = (UserApply) o;

        if (id != userApply.id) return false;
        if (userId != null ? !userId.equals(userApply.userId) : userApply.userId != null) return false;
        if (phone != null ? !phone.equals(userApply.phone) : userApply.phone != null) return false;
        if (type != null ? !type.equals(userApply.type) : userApply.type != null) return false;
        if (applyTime != null ? !applyTime.equals(userApply.applyTime) : userApply.applyTime != null) return false;
        if (dealTime != null ? !dealTime.equals(userApply.dealTime) : userApply.dealTime != null) return false;
        if (assign != null ? !assign.equals(userApply.assign) : userApply.assign != null) return false;
        if (admin != null ? !admin.equals(userApply.admin) : userApply.admin != null) return false;
        if (state != null ? !state.equals(userApply.state) : userApply.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (applyTime != null ? applyTime.hashCode() : 0);
        result = 31 * result + (dealTime != null ? dealTime.hashCode() : 0);
        result = 31 * result + (assign != null ? assign.hashCode() : 0);
        result = 31 * result + (admin != null ? admin.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}

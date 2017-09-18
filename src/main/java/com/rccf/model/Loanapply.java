package com.rccf.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Loanapply {
    private int id;
    private String userId;
    private String realName;
    private String phone;
    private Integer type;
    private Integer wantMoney;
    private Timestamp wantTime;
    private Timestamp createTime;
    private Integer stat;
    private String channelPhone;
    private String clerkPhone;
    private String clerkName;
    private String channelName;

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
    @Column(name = "real_name")
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "want_money")
    public Integer getWantMoney() {
        return wantMoney;
    }

    public void setWantMoney(Integer wantMoney) {
        this.wantMoney = wantMoney;
    }

    @Basic
    @Column(name = "want_time")
    public Timestamp getWantTime() {
        return wantTime;
    }

    public void setWantTime(Timestamp wantTime) {
        this.wantTime = wantTime;
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
    @Column(name = "stat")
    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    @Basic
    @Column(name = "channel_phone")
    public String getChannelPhone() {
        return channelPhone;
    }

    public void setChannelPhone(String channelPhone) {
        this.channelPhone = channelPhone;
    }

    @Basic
    @Column(name = "clerk_phone")
    public String getClerkPhone() {
        return clerkPhone;
    }

    public void setClerkPhone(String clerkPhone) {
        this.clerkPhone = clerkPhone;
    }

    @Basic
    @Column(name = "clerk_name")
    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    @Basic
    @Column(name = "channel_name")
    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Loanapply loanapply = (Loanapply) o;

        if (id != loanapply.id) return false;
        if (userId != null ? !userId.equals(loanapply.userId) : loanapply.userId != null) return false;
        if (realName != null ? !realName.equals(loanapply.realName) : loanapply.realName != null) return false;
        if (phone != null ? !phone.equals(loanapply.phone) : loanapply.phone != null) return false;
        if (type != null ? !type.equals(loanapply.type) : loanapply.type != null) return false;
        if (wantMoney != null ? !wantMoney.equals(loanapply.wantMoney) : loanapply.wantMoney != null) return false;
        if (wantTime != null ? !wantTime.equals(loanapply.wantTime) : loanapply.wantTime != null) return false;
        if (createTime != null ? !createTime.equals(loanapply.createTime) : loanapply.createTime != null) return false;
        if (stat != null ? !stat.equals(loanapply.stat) : loanapply.stat != null) return false;
        if (channelPhone != null ? !channelPhone.equals(loanapply.channelPhone) : loanapply.channelPhone != null)
            return false;
        if (clerkPhone != null ? !clerkPhone.equals(loanapply.clerkPhone) : loanapply.clerkPhone != null) return false;
        if (clerkName != null ? !clerkName.equals(loanapply.clerkName) : loanapply.clerkName != null) return false;
        if (channelName != null ? !channelName.equals(loanapply.channelName) : loanapply.channelName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (realName != null ? realName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (wantMoney != null ? wantMoney.hashCode() : 0);
        result = 31 * result + (wantTime != null ? wantTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (stat != null ? stat.hashCode() : 0);
        result = 31 * result + (channelPhone != null ? channelPhone.hashCode() : 0);
        result = 31 * result + (clerkPhone != null ? clerkPhone.hashCode() : 0);
        result = 31 * result + (clerkName != null ? clerkName.hashCode() : 0);
        result = 31 * result + (channelName != null ? channelName.hashCode() : 0);
        return result;
    }
}

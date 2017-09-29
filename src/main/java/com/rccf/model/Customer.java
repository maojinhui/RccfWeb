package com.rccf.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Customer {
    private int id;
    private String name;
    private Integer sex;
    private String idcard;
    private Integer age;
    private String phone;
    private Integer wantMoney;
    private Date wantTime;
    private Integer useType;
    private Integer useCycleYear;
    private Integer useCycleMonth;
    private Integer useCycleDay;
    private Integer repaymentType;
    private Integer rank;
    private String clerk;
    private String process;
    private Timestamp createTime;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "sex")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "idcard")
    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
    @Column(name = "want_money")
    public Integer getWantMoney() {
        return wantMoney;
    }

    public void setWantMoney(Integer wantMoney) {
        this.wantMoney = wantMoney;
    }

    @Basic
    @Column(name = "want_time")
    public Date getWantTime() {
        return wantTime;
    }

    public void setWantTime(Date wantTime) {
        this.wantTime = wantTime;
    }

    @Basic
    @Column(name = "use_type")
    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    @Basic
    @Column(name = "use_cycle_year")
    public Integer getUseCycleYear() {
        return useCycleYear;
    }

    public void setUseCycleYear(Integer useCycleYear) {
        this.useCycleYear = useCycleYear;
    }

    @Basic
    @Column(name = "use_cycle_month")
    public Integer getUseCycleMonth() {
        return useCycleMonth;
    }

    public void setUseCycleMonth(Integer useCycleMonth) {
        this.useCycleMonth = useCycleMonth;
    }

    @Basic
    @Column(name = "use_cycle_day")
    public Integer getUseCycleDay() {
        return useCycleDay;
    }

    public void setUseCycleDay(Integer useCycleDay) {
        this.useCycleDay = useCycleDay;
    }

    @Basic
    @Column(name = "repayment_type")
    public Integer getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(Integer repaymentType) {
        this.repaymentType = repaymentType;
    }

    @Basic
    @Column(name = "rank")
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Basic
    @Column(name = "clerk")
    public String getClerk() {
        return clerk;
    }

    public void setClerk(String clerk) {
        this.clerk = clerk;
    }

    @Basic
    @Column(name = "process")
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
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

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        if (sex != null ? !sex.equals(customer.sex) : customer.sex != null) return false;
        if (idcard != null ? !idcard.equals(customer.idcard) : customer.idcard != null) return false;
        if (age != null ? !age.equals(customer.age) : customer.age != null) return false;
        if (phone != null ? !phone.equals(customer.phone) : customer.phone != null) return false;
        if (wantMoney != null ? !wantMoney.equals(customer.wantMoney) : customer.wantMoney != null) return false;
        if (wantTime != null ? !wantTime.equals(customer.wantTime) : customer.wantTime != null) return false;
        if (useType != null ? !useType.equals(customer.useType) : customer.useType != null) return false;
        if (useCycleYear != null ? !useCycleYear.equals(customer.useCycleYear) : customer.useCycleYear != null)
            return false;
        if (useCycleMonth != null ? !useCycleMonth.equals(customer.useCycleMonth) : customer.useCycleMonth != null)
            return false;
        if (useCycleDay != null ? !useCycleDay.equals(customer.useCycleDay) : customer.useCycleDay != null)
            return false;
        if (repaymentType != null ? !repaymentType.equals(customer.repaymentType) : customer.repaymentType != null)
            return false;
        if (rank != null ? !rank.equals(customer.rank) : customer.rank != null) return false;
        if (clerk != null ? !clerk.equals(customer.clerk) : customer.clerk != null) return false;
        if (process != null ? !process.equals(customer.process) : customer.process != null) return false;
        if (createTime != null ? !createTime.equals(customer.createTime) : customer.createTime != null) return false;
        if (state != null ? !state.equals(customer.state) : customer.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (idcard != null ? idcard.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (wantMoney != null ? wantMoney.hashCode() : 0);
        result = 31 * result + (wantTime != null ? wantTime.hashCode() : 0);
        result = 31 * result + (useType != null ? useType.hashCode() : 0);
        result = 31 * result + (useCycleYear != null ? useCycleYear.hashCode() : 0);
        result = 31 * result + (useCycleMonth != null ? useCycleMonth.hashCode() : 0);
        result = 31 * result + (useCycleDay != null ? useCycleDay.hashCode() : 0);
        result = 31 * result + (repaymentType != null ? repaymentType.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        result = 31 * result + (clerk != null ? clerk.hashCode() : 0);
        result = 31 * result + (process != null ? process.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}

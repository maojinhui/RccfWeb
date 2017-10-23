package com.rccf.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "employee_base", schema = "rccf", catalog = "")
public class EmployeeBase {
    private int id;
    private int eid;
    private String nation;
    private String bloodtype;
    private Integer married;
    private Integer political;
    private Date birthday;
    private String idcard;
    private Date beginWorkTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "eid")
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    @Basic
    @Column(name = "nation")
    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    @Basic
    @Column(name = "bloodtype")
    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    @Basic
    @Column(name = "married")
    public Integer getMarried() {
        return married;
    }

    public void setMarried(Integer married) {
        this.married = married;
    }

    @Basic
    @Column(name = "political")
    public Integer getPolitical() {
        return political;
    }

    public void setPolitical(Integer political) {
        this.political = political;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
    @Column(name = "begin_work_time")
    public Date getBeginWorkTime() {
        return beginWorkTime;
    }

    public void setBeginWorkTime(Date beginWorkTime) {
        this.beginWorkTime = beginWorkTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeBase that = (EmployeeBase) o;

        if (id != that.id) return false;
        if (eid != that.eid) return false;
        if (nation != null ? !nation.equals(that.nation) : that.nation != null) return false;
        if (bloodtype != null ? !bloodtype.equals(that.bloodtype) : that.bloodtype != null) return false;
        if (married != null ? !married.equals(that.married) : that.married != null) return false;
        if (political != null ? !political.equals(that.political) : that.political != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (idcard != null ? !idcard.equals(that.idcard) : that.idcard != null) return false;
        if (beginWorkTime != null ? !beginWorkTime.equals(that.beginWorkTime) : that.beginWorkTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + eid;
        result = 31 * result + (nation != null ? nation.hashCode() : 0);
        result = 31 * result + (bloodtype != null ? bloodtype.hashCode() : 0);
        result = 31 * result + (married != null ? married.hashCode() : 0);
        result = 31 * result + (political != null ? political.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (idcard != null ? idcard.hashCode() : 0);
        result = 31 * result + (beginWorkTime != null ? beginWorkTime.hashCode() : 0);
        return result;
    }
}

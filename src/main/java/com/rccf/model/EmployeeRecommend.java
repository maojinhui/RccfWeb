package com.rccf.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "employee_recommend", schema = "rccf", catalog = "")
public class EmployeeRecommend {
    private int id;
    private String code;
    private String phone;
    private String phoneFinxed;
    private String headerImg;
    private String name;
    private Integer sex;
    private Integer age;
    private Timestamp createTime;
    private String admin;
    private Integer recommend;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    @Column(name = "phone_finxed")
    public String getPhoneFinxed() {
        return phoneFinxed;
    }

    public void setPhoneFinxed(String phoneFinxed) {
        this.phoneFinxed = phoneFinxed;
    }

    @Basic
    @Column(name = "header_img")
    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
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
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
    @Column(name = "admin")
    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @Basic
    @Column(name = "recommend")
    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeRecommend that = (EmployeeRecommend) o;

        if (id != that.id) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (phoneFinxed != null ? !phoneFinxed.equals(that.phoneFinxed) : that.phoneFinxed != null) return false;
        if (headerImg != null ? !headerImg.equals(that.headerImg) : that.headerImg != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (admin != null ? !admin.equals(that.admin) : that.admin != null) return false;
        if (recommend != null ? !recommend.equals(that.recommend) : that.recommend != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (phoneFinxed != null ? phoneFinxed.hashCode() : 0);
        result = 31 * result + (headerImg != null ? headerImg.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (admin != null ? admin.hashCode() : 0);
        result = 31 * result + (recommend != null ? recommend.hashCode() : 0);
        return result;
    }
}

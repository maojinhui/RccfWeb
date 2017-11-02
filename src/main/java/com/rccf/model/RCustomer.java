package com.rccf.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "r_customer", schema = "rccf", catalog = "")
public class RCustomer {
    private String id;
    private String name;
    private String phone;
    private Integer sex;
    private Integer age;
    private String housePhone;
    private String idcard;
    private Integer married;
    private Integer educationLevel;
    private String domicile;
    private String birthplace;
    private String children;
    private String email;
    private String qq;
    private String wechat;
    private String addressNow;
    private String liveTime;
    private String hobby;

    @Id
    @Column(name = "id", nullable = false, length = 16)
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 225)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 11)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "sex", nullable = true)
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "house_phone", nullable = true, length = 100)
    public String getHousePhone() {
        return housePhone;
    }

    public void setHousePhone(String housePhone) {
        this.housePhone = housePhone;
    }

    @Basic
    @Column(name = "idcard", nullable = true, length = 18)
    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    @Basic
    @Column(name = "married", nullable = true)
    public Integer getMarried() {
        return married;
    }

    public void setMarried(Integer married) {
        this.married = married;
    }

    @Basic
    @Column(name = "education_level", nullable = true)
    public Integer getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(Integer educationLevel) {
        this.educationLevel = educationLevel;
    }

    @Basic
    @Column(name = "domicile", nullable = true, length = 225)
    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    @Basic
    @Column(name = "birthplace", nullable = true, length = 225)
    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    @Basic
    @Column(name = "children", nullable = true, length = 225)
    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 225)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "qq", nullable = true, length = 32)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "wechat", nullable = true, length = 64)
    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    @Basic
    @Column(name = "address_now", nullable = true, length = 225)
    public String getAddressNow() {
        return addressNow;
    }

    public void setAddressNow(String addressNow) {
        this.addressNow = addressNow;
    }

    @Basic
    @Column(name = "live_time", nullable = true, length = 225)
    public String getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(String liveTime) {
        this.liveTime = liveTime;
    }

    @Basic
    @Column(name = "hobby", nullable = true, length = 225)
    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RCustomer rCustomer = (RCustomer) o;

        if (id != null ? !id.equals(rCustomer.id) : rCustomer.id != null) return false;
        if (name != null ? !name.equals(rCustomer.name) : rCustomer.name != null) return false;
        if (phone != null ? !phone.equals(rCustomer.phone) : rCustomer.phone != null) return false;
        if (sex != null ? !sex.equals(rCustomer.sex) : rCustomer.sex != null) return false;
        if (age != null ? !age.equals(rCustomer.age) : rCustomer.age != null) return false;
        if (housePhone != null ? !housePhone.equals(rCustomer.housePhone) : rCustomer.housePhone != null) return false;
        if (idcard != null ? !idcard.equals(rCustomer.idcard) : rCustomer.idcard != null) return false;
        if (married != null ? !married.equals(rCustomer.married) : rCustomer.married != null) return false;
        if (educationLevel != null ? !educationLevel.equals(rCustomer.educationLevel) : rCustomer.educationLevel != null)
            return false;
        if (domicile != null ? !domicile.equals(rCustomer.domicile) : rCustomer.domicile != null) return false;
        if (birthplace != null ? !birthplace.equals(rCustomer.birthplace) : rCustomer.birthplace != null) return false;
        if (children != null ? !children.equals(rCustomer.children) : rCustomer.children != null) return false;
        if (email != null ? !email.equals(rCustomer.email) : rCustomer.email != null) return false;
        if (qq != null ? !qq.equals(rCustomer.qq) : rCustomer.qq != null) return false;
        if (wechat != null ? !wechat.equals(rCustomer.wechat) : rCustomer.wechat != null) return false;
        if (addressNow != null ? !addressNow.equals(rCustomer.addressNow) : rCustomer.addressNow != null) return false;
        if (liveTime != null ? !liveTime.equals(rCustomer.liveTime) : rCustomer.liveTime != null) return false;
        if (hobby != null ? !hobby.equals(rCustomer.hobby) : rCustomer.hobby != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (housePhone != null ? housePhone.hashCode() : 0);
        result = 31 * result + (idcard != null ? idcard.hashCode() : 0);
        result = 31 * result + (married != null ? married.hashCode() : 0);
        result = 31 * result + (educationLevel != null ? educationLevel.hashCode() : 0);
        result = 31 * result + (domicile != null ? domicile.hashCode() : 0);
        result = 31 * result + (birthplace != null ? birthplace.hashCode() : 0);
        result = 31 * result + (children != null ? children.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (wechat != null ? wechat.hashCode() : 0);
        result = 31 * result + (addressNow != null ? addressNow.hashCode() : 0);
        result = 31 * result + (liveTime != null ? liveTime.hashCode() : 0);
        result = 31 * result + (hobby != null ? hobby.hashCode() : 0);
        return result;
    }
}

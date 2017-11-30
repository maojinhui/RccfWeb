package com.rccf.model;


import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

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
    private Timestamp createTime;
    private String nation;
    private Timestamp adminTime;
    private Integer level;

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

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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
    @Column(name = "admin_time")
    public Timestamp getAdminTime() {
        return adminTime;
    }

    public void setAdminTime(Timestamp adminTime) {
        this.adminTime = adminTime;
    }

    @Basic
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RCustomer rCustomer = (RCustomer) o;
        return Objects.equal(id, rCustomer.id) &&
                Objects.equal(name, rCustomer.name) &&
                Objects.equal(phone, rCustomer.phone) &&
                Objects.equal(sex, rCustomer.sex) &&
                Objects.equal(age, rCustomer.age) &&
                Objects.equal(housePhone, rCustomer.housePhone) &&
                Objects.equal(idcard, rCustomer.idcard) &&
                Objects.equal(married, rCustomer.married) &&
                Objects.equal(educationLevel, rCustomer.educationLevel) &&
                Objects.equal(domicile, rCustomer.domicile) &&
                Objects.equal(birthplace, rCustomer.birthplace) &&
                Objects.equal(children, rCustomer.children) &&
                Objects.equal(email, rCustomer.email) &&
                Objects.equal(qq, rCustomer.qq) &&
                Objects.equal(wechat, rCustomer.wechat) &&
                Objects.equal(addressNow, rCustomer.addressNow) &&
                Objects.equal(liveTime, rCustomer.liveTime) &&
                Objects.equal(hobby, rCustomer.hobby) &&
                Objects.equal(createTime, rCustomer.createTime) &&
                Objects.equal(nation, rCustomer.nation) &&
                Objects.equal(adminTime, rCustomer.adminTime) &&
                Objects.equal(level, rCustomer.level);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, phone, sex, age, nation, housePhone, idcard, married, educationLevel, domicile, birthplace, children, email, qq, wechat, addressNow, liveTime, hobby, createTime, adminTime, level);
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("phone", phone)
                .add("sex", sex)
                .add("age", age)
                .add("housePhone", housePhone)
                .add("idcard", idcard)
                .add("married", married)
                .add("educationLevel", educationLevel)
                .add("domicile", domicile)
                .add("birthplace", birthplace)
                .add("children", children)
                .add("email", email)
                .add("qq", qq)
                .add("wechat", wechat)
                .add("addressNow", addressNow)
                .add("liveTime", liveTime)
                .add("hobby", hobby)
                .add("createTime", createTime)
                .add("nation", nation)
                .add("adminTime", adminTime)
                .add("level", level)
                .toString();
    }
}

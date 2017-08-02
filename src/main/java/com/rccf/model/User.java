package com.rccf.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by greatland on 17/7/12.
 */
@Entity
public class User {
    private String userId;
    private String userName;
    private String password;
    private String realName;
    private String idcard;
    private String phone;
    private String fixedPhone;
    private String signature;
    private String role;
    private String openid;
    private String openType;
    private Byte sex;
    private String accessToken;
    private Integer age;
    private String address;
    private String remarks;
    private String unionId;
    private String regedistChannel;

    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "idcard")
    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
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
    @Column(name = "fixed_phone")
    public String getFixedPhone() {
        return fixedPhone;
    }

    public void setFixedPhone(String fixedPhone) {
        this.fixedPhone = fixedPhone;
    }

    @Basic
    @Column(name = "signature")
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "openid")
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Basic
    @Column(name = "open_type")
    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }

    @Basic
    @Column(name = "sex")
    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "access_token")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (realName != null ? !realName.equals(user.realName) : user.realName != null) return false;
        if (idcard != null ? !idcard.equals(user.idcard) : user.idcard != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (fixedPhone != null ? !fixedPhone.equals(user.fixedPhone) : user.fixedPhone != null) return false;
        if (signature != null ? !signature.equals(user.signature) : user.signature != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        if (openid != null ? !openid.equals(user.openid) : user.openid != null) return false;
        if (openType != null ? !openType.equals(user.openType) : user.openType != null) return false;
        if (sex != null ? !sex.equals(user.sex) : user.sex != null) return false;
        if (accessToken != null ? !accessToken.equals(user.accessToken) : user.accessToken != null) return false;
        if (age != null ? !age.equals(user.age) : user.age != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (remarks != null ? !remarks.equals(user.remarks) : user.remarks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (realName != null ? realName.hashCode() : 0);
        result = 31 * result + (idcard != null ? idcard.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (fixedPhone != null ? fixedPhone.hashCode() : 0);
        result = 31 * result + (signature != null ? signature.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (openid != null ? openid.hashCode() : 0);
        result = 31 * result + (openType != null ? openType.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (accessToken != null ? accessToken.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "union_id")
    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    @Basic
    @Column(name = "regedist_channel")
    public String getRegedistChannel() {
        return regedistChannel;
    }

    public void setRegedistChannel(String regedistChannel) {
        this.regedistChannel = regedistChannel;
    }
}

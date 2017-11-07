package com.rccf.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
    private String unionId;
    private String openType;
    private Integer sex;
    private String accessToken;
    private Integer age;
    private String address;
    private String remarks;
    private String regedistChannel;
    private String headimg;
    private String province;
    private String city;
    private String ticket;
    private int id;

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
    @Column(name = "union_id")
    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
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
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
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

    @Basic
    @Column(name = "regedist_channel")
    public String getRegedistChannel() {
        return regedistChannel;
    }

    public void setRegedistChannel(String regedistChannel) {
        this.regedistChannel = regedistChannel;
    }

    @Basic
    @Column(name = "headimg")
    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    @Basic
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
        if (unionId != null ? !unionId.equals(user.unionId) : user.unionId != null) return false;
        if (openType != null ? !openType.equals(user.openType) : user.openType != null) return false;
        if (sex != null ? !sex.equals(user.sex) : user.sex != null) return false;
        if (accessToken != null ? !accessToken.equals(user.accessToken) : user.accessToken != null) return false;
        if (age != null ? !age.equals(user.age) : user.age != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (remarks != null ? !remarks.equals(user.remarks) : user.remarks != null) return false;
        if (regedistChannel != null ? !regedistChannel.equals(user.regedistChannel) : user.regedistChannel != null)
            return false;
        if (headimg != null ? !headimg.equals(user.headimg) : user.headimg != null) return false;
        if (province != null ? !province.equals(user.province) : user.province != null) return false;
        if (city != null ? !city.equals(user.city) : user.city != null) return false;

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
        result = 31 * result + (unionId != null ? unionId.hashCode() : 0);
        result = 31 * result + (openType != null ? openType.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (accessToken != null ? accessToken.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (regedistChannel != null ? regedistChannel.hashCode() : 0);
        result = 31 * result + (headimg != null ? headimg.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "ticket")
    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Basic
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

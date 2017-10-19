package com.rccf.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Employee {
    private int id;
    private String code;
    private String phone;
    private String phoneFixed;
    private String name;
    private Integer sex;
    private Integer age;
    private Timestamp entryTime;
    private String duties;
    private Timestamp createTime;
    private String admin;
    private Integer recommend;
    private String leader;
    private String department;
    private Integer role;
    private String password;
    private Integer state;
    private String duptyDirector;
    private String duptyDirectorName;
    private String director;
    private String directorName;
    private Timestamp leaveTime;
    private String email;
    private Timestamp turnTime;
    private Integer accessControlNumber;
    private String nameOnce;
    private String nameEn;
    private String leaveReason;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column(name = "phone_fixed")
    public String getPhoneFixed() {
        return phoneFixed;
    }

    public void setPhoneFixed(String phoneFixed) {
        this.phoneFixed = phoneFixed;
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
    @Column(name = "entry_time")
    public Timestamp getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Timestamp entryTime) {
        this.entryTime = entryTime;
    }

    @Basic
    @Column(name = "duties")
    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
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

    @Basic
    @Column(name = "leader")
    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    @Basic
    @Column(name = "department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (code != null ? !code.equals(employee.code) : employee.code != null) return false;
        if (phone != null ? !phone.equals(employee.phone) : employee.phone != null) return false;
        if (phoneFixed != null ? !phoneFixed.equals(employee.phoneFixed) : employee.phoneFixed != null) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (sex != null ? !sex.equals(employee.sex) : employee.sex != null) return false;
        if (age != null ? !age.equals(employee.age) : employee.age != null) return false;
        if (entryTime != null ? !entryTime.equals(employee.entryTime) : employee.entryTime != null) return false;
        if (duties != null ? !duties.equals(employee.duties) : employee.duties != null) return false;
        if (createTime != null ? !createTime.equals(employee.createTime) : employee.createTime != null) return false;
        if (admin != null ? !admin.equals(employee.admin) : employee.admin != null) return false;
        if (recommend != null ? !recommend.equals(employee.recommend) : employee.recommend != null) return false;
        if (leader != null ? !leader.equals(employee.leader) : employee.leader != null) return false;
        if (department != null ? !department.equals(employee.department) : employee.department != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (phoneFixed != null ? phoneFixed.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (entryTime != null ? entryTime.hashCode() : 0);
        result = 31 * result + (duties != null ? duties.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (admin != null ? admin.hashCode() : 0);
        result = 31 * result + (recommend != null ? recommend.hashCode() : 0);
        result = 31 * result + (leader != null ? leader.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "role")
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
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
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "dupty_director")
    public String getDuptyDirector() {
        return duptyDirector;
    }

    public void setDuptyDirector(String duptyDirector) {
        this.duptyDirector = duptyDirector;
    }

    @Basic
    @Column(name = "dupty_director_name")
    public String getDuptyDirectorName() {
        return duptyDirectorName;
    }

    public void setDuptyDirectorName(String duptyDirectorName) {
        this.duptyDirectorName = duptyDirectorName;
    }

    @Basic
    @Column(name = "director")
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Basic
    @Column(name = "director_name")
    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    @Basic
    @Column(name = "leave_time")
    public Timestamp getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Timestamp leaveTime) {
        this.leaveTime = leaveTime;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "turn_time")
    public Timestamp getTurnTime() {
        return turnTime;
    }

    public void setTurnTime(Timestamp turnTime) {
        this.turnTime = turnTime;
    }

    @Basic
    @Column(name = "access_control_number")
    public Integer getAccessControlNumber() {
        return accessControlNumber;
    }

    public void setAccessControlNumber(Integer accessControlNumber) {
        this.accessControlNumber = accessControlNumber;
    }

    @Basic
    @Column(name = "name_once")
    public String getNameOnce() {
        return nameOnce;
    }

    public void setNameOnce(String nameOnce) {
        this.nameOnce = nameOnce;
    }

    @Basic
    @Column(name = "name_en")
    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    @Basic
    @Column(name = "leave_reason")
    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }
}

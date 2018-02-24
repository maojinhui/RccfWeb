package com.rccf.model.gzh.accpet;

import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "accepted_temp", schema = "rccf", catalog = "")
public class AcceptedTemp {
    private String id;
    private String customerId;
    private String customerName;
    private String customerPhone;
    private String customerIdcard;
    private Integer customerLoanType;
    private String customerFile;
    private String employee;
    private String deputy;
    private String director;
    private Double customerWantmoney;
    private Double serviceProportion;
    private Integer houqi;
    private String produceInfo;
    private String channelFee;
    private String materialFee;
    private String sanfangFee;
    private Integer state;
    private String employeeName;
    private String deputyName;
    private String directorName;
    private String houqiName;
    private Timestamp acceptTime;
    private String content;
    private String acceptNumber;
    private Integer acceptId;
    private String letterNumber;
    private Timestamp createTime;

    @Id
    @Column(name = "id", nullable = false, length = 64)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "customer_id", nullable = true, length = 64)
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "customer_name", nullable = true, length = 255)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "customer_phone", nullable = true, length = 11)
    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Basic
    @Column(name = "customer_idcard", nullable = true, length = 18)
    public String getCustomerIdcard() {
        return customerIdcard;
    }

    public void setCustomerIdcard(String customerIdcard) {
        this.customerIdcard = customerIdcard;
    }

    @Basic
    @Column(name = "customer_loan_type", nullable = true)
    public Integer getCustomerLoanType() {
        return customerLoanType;
    }

    public void setCustomerLoanType(Integer customerLoanType) {
        this.customerLoanType = customerLoanType;
    }

    @Basic
    @Column(name = "customer_file", nullable = true, length = -1)
    public String getCustomerFile() {
        return customerFile;
    }

    public void setCustomerFile(String customerFile) {
        this.customerFile = customerFile;
    }

    @Basic
    @Column(name = "employee", nullable = true, length = 255)
    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    @Basic
    @Column(name = "deputy", nullable = true, length = 255)
    public String getDeputy() {
        return deputy;
    }

    public void setDeputy(String deputy) {
        this.deputy = deputy;
    }

    @Basic
    @Column(name = "director", nullable = true, length = 255)
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Basic
    @Column(name = "customer_wantmoney", nullable = true, precision = 0)
    public Double getCustomerWantmoney() {
        return customerWantmoney;
    }

    public void setCustomerWantmoney(Double customerWantmoney) {
        this.customerWantmoney = customerWantmoney;
    }

    @Basic
    @Column(name = "service_proportion", nullable = true, precision = 0)
    public Double getServiceProportion() {
        return serviceProportion;
    }

    public void setServiceProportion(Double serviceProportion) {
        this.serviceProportion = serviceProportion;
    }

    @Basic
    @Column(name = "houqi", nullable = true)
    public Integer getHouqi() {
        return houqi;
    }

    public void setHouqi(Integer houqi) {
        this.houqi = houqi;
    }

    @Basic
    @Column(name = "produce_info", nullable = true, length = -1)
    public String getProduceInfo() {
        return produceInfo;
    }

    public void setProduceInfo(String produceInfo) {
        this.produceInfo = produceInfo;
    }

    @Basic
    @Column(name = "channel_fee", nullable = true, length = -1)
    public String getChannelFee() {
        return channelFee;
    }

    public void setChannelFee(String channelFee) {
        this.channelFee = channelFee;
    }

    @Basic
    @Column(name = "material_fee", nullable = true, length = -1)
    public String getMaterialFee() {
        return materialFee;
    }

    public void setMaterialFee(String materialFee) {
        this.materialFee = materialFee;
    }

    @Basic
    @Column(name = "sanfang_fee", nullable = true, length = -1)
    public String getSanfangFee() {
        return sanfangFee;
    }

    public void setSanfangFee(String sanfangFee) {
        this.sanfangFee = sanfangFee;
    }

    @Basic
    @Column(name = "state", nullable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "employee_name", nullable = true, length = 255)
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Basic
    @Column(name = "deputy_name", nullable = true, length = 255)
    public String getDeputyName() {
        return deputyName;
    }

    public void setDeputyName(String deputyName) {
        this.deputyName = deputyName;
    }

    @Basic
    @Column(name = "director_name", nullable = true, length = 255)
    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    @Basic
    @Column(name = "houqi_name", nullable = true, length = 255)
    public String getHouqiName() {
        return houqiName;
    }

    public void setHouqiName(String houqiName) {
        this.houqiName = houqiName;
    }

    @Basic
    @Column(name = "accept_time", nullable = true)
    public Timestamp getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Timestamp acceptTime) {
        this.acceptTime = acceptTime;
    }

    @Basic
    @Column(name = "content", nullable = true, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "acceptNumber", nullable = true, length = 255)
    public String getAcceptNumber() {
        return acceptNumber;
    }

    public void setAcceptNumber(String acceptNumber) {
        this.acceptNumber = acceptNumber;
    }

    @Basic
    @Column(name = "accept_id", nullable = true)
    public Integer getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(Integer acceptId) {
        this.acceptId = acceptId;
    }

    @Basic
    @Column(name = "letterNumber", nullable = true, length = 255)
    public String getLetterNumber() {
        return letterNumber;
    }

    public void setLetterNumber(String letterNumber) {
        this.letterNumber = letterNumber;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcceptedTemp that = (AcceptedTemp) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(customerId, that.customerId) &&
                Objects.equal(customerName, that.customerName) &&
                Objects.equal(customerPhone, that.customerPhone) &&
                Objects.equal(customerIdcard, that.customerIdcard) &&
                Objects.equal(customerLoanType, that.customerLoanType) &&
                Objects.equal(customerFile, that.customerFile) &&
                Objects.equal(employee, that.employee) &&
                Objects.equal(deputy, that.deputy) &&
                Objects.equal(director, that.director) &&
                Objects.equal(customerWantmoney, that.customerWantmoney) &&
                Objects.equal(serviceProportion, that.serviceProportion) &&
                Objects.equal(houqi, that.houqi) &&
                Objects.equal(produceInfo, that.produceInfo) &&
                Objects.equal(channelFee, that.channelFee) &&
                Objects.equal(materialFee, that.materialFee) &&
                Objects.equal(sanfangFee, that.sanfangFee) &&
                Objects.equal(state, that.state) &&
                Objects.equal(employeeName, that.employeeName) &&
                Objects.equal(deputyName, that.deputyName) &&
                Objects.equal(directorName, that.directorName) &&
                Objects.equal(houqiName, that.houqiName) &&
                Objects.equal(acceptTime, that.acceptTime) &&
                Objects.equal(content, that.content) &&
                Objects.equal(acceptNumber, that.acceptNumber) &&
                Objects.equal(acceptId, that.acceptId) &&
                Objects.equal(letterNumber, that.letterNumber) &&
                Objects.equal(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, customerId, customerName, customerPhone, customerIdcard, customerLoanType, customerFile, employee, deputy, director, customerWantmoney, serviceProportion, houqi, produceInfo, channelFee, materialFee, sanfangFee, state, employeeName, deputyName, directorName, houqiName, acceptTime, content, acceptNumber, acceptId, letterNumber, createTime);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("customerId", customerId)
                .add("customerName", customerName)
                .add("customerPhone", customerPhone)
                .add("customerIdcard", customerIdcard)
                .add("customerLoanType", customerLoanType)
                .add("customerFile", customerFile)
                .add("employee", employee)
                .add("deputy", deputy)
                .add("director", director)
                .add("customerWantmoney", customerWantmoney)
                .add("serviceProportion", serviceProportion)
                .add("houqi", houqi)
                .add("produceInfo", produceInfo)
                .add("channelFee", channelFee)
                .add("materialFee", materialFee)
                .add("sanfangFee", sanfangFee)
                .add("state", state)
                .add("employeeName", employeeName)
                .add("deputyName", deputyName)
                .add("directorName", directorName)
                .add("houqiName", houqiName)
                .add("acceptTime", acceptTime)
                .add("content", content)
                .add("acceptNumber", acceptNumber)
                .add("acceptId", acceptId)
                .add("letterNumber", letterNumber)
                .add("createTime", createTime)
                .toString();
    }
}

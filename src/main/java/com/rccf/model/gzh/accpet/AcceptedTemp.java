package com.rccf.model.gzh.accpet;

import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
    private Double channelFee;
    private Double materialFee;
    private Double sanfangFee;
    private Integer state;

    @Id
    @Column(name = "id", nullable = false, length = 64)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "uuid")
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
    @Column(name = "channel_fee", nullable = true, precision = 0)
    public Double getChannelFee() {
        return channelFee;
    }

    public void setChannelFee(Double channelFee) {
        this.channelFee = channelFee;
    }

    @Basic
    @Column(name = "material_fee", nullable = true, precision = 0)
    public Double getMaterialFee() {
        return materialFee;
    }

    public void setMaterialFee(Double materialFee) {
        this.materialFee = materialFee;
    }

    @Basic
    @Column(name = "sanfang_fee", nullable = true, precision = 0)
    public Double getSanfangFee() {
        return sanfangFee;
    }

    public void setSanfangFee(Double sanfangFee) {
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
                Objects.equal(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, customerId, customerName, customerPhone, customerIdcard, customerLoanType, customerFile, employee, deputy, director, customerWantmoney, serviceProportion, houqi, produceInfo, channelFee, materialFee, sanfangFee, state);
    }
}

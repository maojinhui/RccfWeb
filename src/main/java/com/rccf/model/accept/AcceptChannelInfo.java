package com.rccf.model.accept;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accept_channel_info", schema = "rccf", catalog = "")
public class AcceptChannelInfo {
    private int id;
    private Integer acceptId;
    private String agencyName;
    private String productName;
    private String contactName;
    private String contactPhone;
    private Double loanAmount;
    private String faceSign;
    private String content;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "agency_name", nullable = true, length = 255)
    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    @Basic
    @Column(name = "product_name", nullable = true, length = 255)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "contact_name", nullable = true, length = 255)
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Basic
    @Column(name = "contact_phone", nullable = true, length = 255)
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Basic
    @Column(name = "loan_amount", nullable = true, precision = 2)
    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    @Basic
    @Column(name = "face_sign", nullable = true, length = 255)
    public String getFaceSign() {
        return faceSign;
    }

    public void setFaceSign(String faceSign) {
        this.faceSign = faceSign;
    }

    @Basic
    @Column(name = "content", nullable = true, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcceptChannelInfo that = (AcceptChannelInfo) o;
        return id == that.id &&
                Objects.equal(acceptId, that.acceptId) &&
                Objects.equal(agencyName, that.agencyName) &&
                Objects.equal(productName, that.productName) &&
                Objects.equal(contactName, that.contactName) &&
                Objects.equal(contactPhone, that.contactPhone) &&
                Objects.equal(loanAmount, that.loanAmount) &&
                Objects.equal(faceSign, that.faceSign) &&
                Objects.equal(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, acceptId, agencyName, productName, contactName, contactPhone, loanAmount, faceSign, content);
    }
}

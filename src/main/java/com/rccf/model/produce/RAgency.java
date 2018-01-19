package com.rccf.model.produce;

import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "r_agency", schema = "rccf", catalog = "")
public class RAgency {
    private String id;
    private Integer productType;
    private String agencyName;
    private String productName;
    private String contactName;
    private String contactPhone;
    private String contactWechat;
    private String contactEmail;
    private String contactDupty;
    private String description;
    private String recommend;
    private Date entryTime;
    private Timestamp createTime;
    private Integer createPerson;
    private String annex;
    private String annexUrl;
    private Integer state;
    private Integer updatePerson;
    private Timestamp updateTime;
    private Integer auditState;
    private Integer reviewState;

    @Id
    @Column(name = "id", nullable = false, length = 64)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "product_type", nullable = true)
    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    @Basic
    @Column(name = "agency_name", nullable = false, length = 255)
    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    @Basic
    @Column(name = "product_name", nullable = false, length = 255)
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
    @Column(name = "contact_phone", nullable = true, length = 11)
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Basic
    @Column(name = "contact_wechat", nullable = true, length = 128)
    public String getContactWechat() {
        return contactWechat;
    }

    public void setContactWechat(String contactWechat) {
        this.contactWechat = contactWechat;
    }

    @Basic
    @Column(name = "contact_email", nullable = true, length = 255)
    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Basic
    @Column(name = "contact_dupty", nullable = true, length = 255)
    public String getContactDupty() {
        return contactDupty;
    }

    public void setContactDupty(String contactDupty) {
        this.contactDupty = contactDupty;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "recommend", nullable = true, length = 255)
    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    @Basic
    @Column(name = "entry_time", nullable = true)
    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "create_person", nullable = true)
    public Integer getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Integer createPerson) {
        this.createPerson = createPerson;
    }

    @Basic
    @Column(name = "annex", nullable = true, length = -1)
    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
    }

    @Basic
    @Column(name = "annex_url", nullable = true, length = 255)
    public String getAnnexUrl() {
        return annexUrl;
    }

    public void setAnnexUrl(String annexUrl) {
        this.annexUrl = annexUrl;
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
    @Column(name = "update_person", nullable = true)
    public Integer getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(Integer updatePerson) {
        this.updatePerson = updatePerson;
    }

    @Basic
    @Column(name = "update_time", nullable = true)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "audit_state", nullable = true)
    public Integer getAuditState() {
        return auditState;
    }

    public void setAuditState(Integer auditState) {
        this.auditState = auditState;
    }

    @Basic
    @Column(name = "review_state", nullable = true)
    public Integer getReviewState() {
        return reviewState;
    }

    public void setReviewState(Integer reviewState) {
        this.reviewState = reviewState;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RAgency rAgency = (RAgency) o;
        return Objects.equal(id, rAgency.id) &&
                Objects.equal(productType, rAgency.productType) &&
                Objects.equal(agencyName, rAgency.agencyName) &&
                Objects.equal(productName, rAgency.productName) &&
                Objects.equal(contactName, rAgency.contactName) &&
                Objects.equal(contactPhone, rAgency.contactPhone) &&
                Objects.equal(contactWechat, rAgency.contactWechat) &&
                Objects.equal(contactEmail, rAgency.contactEmail) &&
                Objects.equal(contactDupty, rAgency.contactDupty) &&
                Objects.equal(description, rAgency.description) &&
                Objects.equal(recommend, rAgency.recommend) &&
                Objects.equal(entryTime, rAgency.entryTime) &&
                Objects.equal(createTime, rAgency.createTime) &&
                Objects.equal(createPerson, rAgency.createPerson) &&
                Objects.equal(annex, rAgency.annex) &&
                Objects.equal(annexUrl, rAgency.annexUrl) &&
                Objects.equal(state, rAgency.state) &&
                Objects.equal(updatePerson, rAgency.updatePerson) &&
                Objects.equal(updateTime, rAgency.updateTime) &&
                Objects.equal(auditState, rAgency.auditState) &&
                Objects.equal(reviewState, rAgency.reviewState);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, productType, agencyName, productName, contactName, contactPhone, contactWechat, contactEmail, contactDupty, description, recommend, entryTime, createTime, createPerson, annex, annexUrl, state, updatePerson, updateTime, auditState, reviewState);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("productType", productType)
                .add("agencyName", agencyName)
                .add("productName", productName)
                .add("contactName", contactName)
                .add("contactPhone", contactPhone)
                .add("contactWechat", contactWechat)
                .add("contactEmail", contactEmail)
                .add("contactDupty", contactDupty)
                .add("description", description)
                .add("recommend", recommend)
                .add("entryTime", entryTime)
                .add("createTime", createTime)
                .add("createPerson", createPerson)
                .add("annex", annex)
                .add("annexUrl", annexUrl)
                .add("state", state)
                .add("updatePerson", updatePerson)
                .add("updateTime", updateTime)
                .add("auditState", auditState)
                .add("reviewState", reviewState)
                .toString();
    }
}

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
    private String recommend;
    private String dockingPeople;
    private String address;
    private String shangkou;
    private String fanfei;
    private Date entryTime;
    private String contactName;
    private String contactPhone;
    private String contactWechat;
    private String contactEmail;
    private String contactDupty;
    private String advantages;
    private String beizhu;
    private Timestamp createTime;
    private Integer createPerson;
    private String annex;
    private String annexUrl;
    private Integer state;
    private Integer auditState;
    private Integer reviewState;
    private String channelSpecial;

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
    @Column(name = "recommend", nullable = true, length = 255)
    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    @Basic
    @Column(name = "docking_people", nullable = true, length = 255)
    public String getDockingPeople() {
        return dockingPeople;
    }

    public void setDockingPeople(String dockingPeople) {
        this.dockingPeople = dockingPeople;
    }

    @Basic
    @Column(name = "address", nullable = true, length = -1)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "shangkou", nullable = true, length = -1)
    public String getShangkou() {
        return shangkou;
    }

    public void setShangkou(String shangkou) {
        this.shangkou = shangkou;
    }

    @Basic
    @Column(name = "fanfei", nullable = true, length = -1)
    public String getFanfei() {
        return fanfei;
    }

    public void setFanfei(String fanfei) {
        this.fanfei = fanfei;
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
    @Column(name = "advantages", nullable = true, length = -1)
    public String getAdvantages() {
        return advantages;
    }

    public void setAdvantages(String advantages) {
        this.advantages = advantages;
    }

    @Basic
    @Column(name = "beizhu", nullable = true, length = -1)
    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
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

    @Basic
    @Column(name = "channel_special", nullable = true, length = -1)
    public String getChannelSpecial() {
        return channelSpecial;
    }

    public void setChannelSpecial(String channelSpecial) {
        this.channelSpecial = channelSpecial;
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
                Objects.equal(recommend, rAgency.recommend) &&
                Objects.equal(dockingPeople, rAgency.dockingPeople) &&
                Objects.equal(address, rAgency.address) &&
                Objects.equal(shangkou, rAgency.shangkou) &&
                Objects.equal(fanfei, rAgency.fanfei) &&
                Objects.equal(entryTime, rAgency.entryTime) &&
                Objects.equal(contactName, rAgency.contactName) &&
                Objects.equal(contactPhone, rAgency.contactPhone) &&
                Objects.equal(contactWechat, rAgency.contactWechat) &&
                Objects.equal(contactEmail, rAgency.contactEmail) &&
                Objects.equal(contactDupty, rAgency.contactDupty) &&
                Objects.equal(advantages, rAgency.advantages) &&
                Objects.equal(beizhu, rAgency.beizhu) &&
                Objects.equal(createTime, rAgency.createTime) &&
                Objects.equal(createPerson, rAgency.createPerson) &&
                Objects.equal(annex, rAgency.annex) &&
                Objects.equal(annexUrl, rAgency.annexUrl) &&
                Objects.equal(state, rAgency.state) &&
                Objects.equal(auditState, rAgency.auditState) &&
                Objects.equal(reviewState, rAgency.reviewState) &&
                Objects.equal(channelSpecial, rAgency.channelSpecial);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, productType, agencyName, productName, recommend, dockingPeople, address, shangkou, fanfei, entryTime, contactName, contactPhone, contactWechat, contactEmail, contactDupty, advantages, beizhu, createTime, createPerson, annex, annexUrl, state, auditState, reviewState, channelSpecial);
    }
}

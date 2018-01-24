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
    private Integer canDifficult;

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

    @Basic
    @Column(name = "can_difficult", nullable = true)
    public Integer getCanDifficult() {
        return canDifficult;
    }

    public void setCanDifficult(Integer canDifficult) {
        this.canDifficult = canDifficult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RAgency agency = (RAgency) o;
        return Objects.equal(id, agency.id) &&
                Objects.equal(productType, agency.productType) &&
                Objects.equal(agencyName, agency.agencyName) &&
                Objects.equal(productName, agency.productName) &&
                Objects.equal(recommend, agency.recommend) &&
                Objects.equal(dockingPeople, agency.dockingPeople) &&
                Objects.equal(address, agency.address) &&
                Objects.equal(shangkou, agency.shangkou) &&
                Objects.equal(fanfei, agency.fanfei) &&
                Objects.equal(entryTime, agency.entryTime) &&
                Objects.equal(contactName, agency.contactName) &&
                Objects.equal(contactPhone, agency.contactPhone) &&
                Objects.equal(contactWechat, agency.contactWechat) &&
                Objects.equal(contactEmail, agency.contactEmail) &&
                Objects.equal(contactDupty, agency.contactDupty) &&
                Objects.equal(advantages, agency.advantages) &&
                Objects.equal(beizhu, agency.beizhu) &&
                Objects.equal(createTime, agency.createTime) &&
                Objects.equal(createPerson, agency.createPerson) &&
                Objects.equal(annex, agency.annex) &&
                Objects.equal(annexUrl, agency.annexUrl) &&
                Objects.equal(state, agency.state) &&
                Objects.equal(auditState, agency.auditState) &&
                Objects.equal(reviewState, agency.reviewState) &&
                Objects.equal(channelSpecial, agency.channelSpecial) &&
                Objects.equal(canDifficult, agency.canDifficult);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, productType, agencyName, productName, recommend, dockingPeople, address, shangkou, fanfei, entryTime, contactName, contactPhone, contactWechat, contactEmail, contactDupty, advantages, beizhu, createTime, createPerson, annex, annexUrl, state, auditState, reviewState, channelSpecial, canDifficult);
    }
}

package com.rccf.model.produce;

import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "a_produce_audit_log", schema = "rccf", catalog = "")
public class AProduceAuditLog {
    private int id;
    private Integer produceId;
    private Integer submit;
    private Timestamp submitTime;
    private Integer audit;
    private Timestamp auditTime;
    private String auditOpinion;
    private Integer produceType;
    private Integer state;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "produce_id")
    public Integer getProduceId() {
        return produceId;
    }

    public void setProduceId(Integer produceId) {
        this.produceId = produceId;
    }

    @Basic
    @Column(name = "submit")
    public Integer getSubmit() {
        return submit;
    }

    public void setSubmit(Integer submit) {
        this.submit = submit;
    }

    @Basic
    @Column(name = "submit_time")
    public Timestamp getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }

    @Basic
    @Column(name = "audit")
    public Integer getAudit() {
        return audit;
    }

    public void setAudit(Integer audit) {
        this.audit = audit;
    }

    @Basic
    @Column(name = "audit_time")
    public Timestamp getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Timestamp auditTime) {
        this.auditTime = auditTime;
    }

    @Basic
    @Column(name = "audit_opinion")
    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AProduceAuditLog that = (AProduceAuditLog) o;
        return id == that.id &&
                Objects.equal(produceId, that.produceId) &&
                Objects.equal(submit, that.submit) &&
                Objects.equal(submitTime, that.submitTime) &&
                Objects.equal(audit, that.audit) &&
                Objects.equal(auditTime, that.auditTime) &&
                Objects.equal(auditOpinion, that.auditOpinion);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, produceId, submit, submitTime, audit, auditTime, auditOpinion);
    }

    @Basic
    @Column(name = "produce_type")
    public Integer getProduceType() {
        return produceType;
    }

    public void setProduceType(Integer produceType) {
        this.produceType = produceType;
    }

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}

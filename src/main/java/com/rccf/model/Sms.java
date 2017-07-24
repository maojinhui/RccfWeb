package com.rccf.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by greatland on 17/7/14.
 */
@Entity
public class Sms {
    private int id;
    private String phone;
    private String code;
    private Timestamp sendtime;
    private Integer resultCode;
    private String resultMsg;
    private String resultSid;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "sendtime")
    public Timestamp getSendtime() {
        return sendtime;
    }

    public void setSendtime(Timestamp sendtime) {
        this.sendtime = sendtime;
    }

    @Basic
    @Column(name = "result_code")
    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    @Basic
    @Column(name = "result_msg")
    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    @Basic
    @Column(name = "result_sid")
    public String getResultSid() {
        return resultSid;
    }

    public void setResultSid(String resultSid) {
        this.resultSid = resultSid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sms sms = (Sms) o;

        if (id != sms.id) return false;
        if (phone != null ? !phone.equals(sms.phone) : sms.phone != null) return false;
        if (code != null ? !code.equals(sms.code) : sms.code != null) return false;
        if (sendtime != null ? !sendtime.equals(sms.sendtime) : sms.sendtime != null) return false;
        if (resultCode != null ? !resultCode.equals(sms.resultCode) : sms.resultCode != null) return false;
        if (resultMsg != null ? !resultMsg.equals(sms.resultMsg) : sms.resultMsg != null) return false;
        if (resultSid != null ? !resultSid.equals(sms.resultSid) : sms.resultSid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (sendtime != null ? sendtime.hashCode() : 0);
        result = 31 * result + (resultCode != null ? resultCode.hashCode() : 0);
        result = 31 * result + (resultMsg != null ? resultMsg.hashCode() : 0);
        result = 31 * result + (resultSid != null ? resultSid.hashCode() : 0);
        return result;
    }
}

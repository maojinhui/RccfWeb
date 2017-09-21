package com.rccf.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "accept_process", schema = "rccf", catalog = "")
public class AcceptProcess {
    private int id;
    private Integer acceptId;
    private String process;
    private Timestamp updateTime;
    private String admin;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "accept_id")
    public Integer getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(Integer acceptId) {
        this.acceptId = acceptId;
    }

    @Basic
    @Column(name = "process")
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "admin")
    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AcceptProcess process1 = (AcceptProcess) o;

        if (id != process1.id) return false;
        if (acceptId != null ? !acceptId.equals(process1.acceptId) : process1.acceptId != null) return false;
        if (process != null ? !process.equals(process1.process) : process1.process != null) return false;
        if (updateTime != null ? !updateTime.equals(process1.updateTime) : process1.updateTime != null) return false;
        if (admin != null ? !admin.equals(process1.admin) : process1.admin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (acceptId != null ? acceptId.hashCode() : 0);
        result = 31 * result + (process != null ? process.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (admin != null ? admin.hashCode() : 0);
        return result;
    }
}

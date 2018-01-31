package com.rccf.model.gzh.accpet;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "accepted_temp_log", schema = "rccf", catalog = "")
public class AcceptedTempLog {
    private int id;
    private String acceptedTempId;
    private Integer createPerson;
    private Timestamp createTime;
    private String oldData;
    private String newData;
    private Integer state;
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
    @Column(name = "accepted_temp_id", nullable = true, length = 64)
    public String getAcceptedTempId() {
        return acceptedTempId;
    }

    public void setAcceptedTempId(String acceptedTempId) {
        this.acceptedTempId = acceptedTempId;
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
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "old_data", nullable = true, length = -1)
    public String getOldData() {
        return oldData;
    }

    public void setOldData(String oldData) {
        this.oldData = oldData;
    }

    @Basic
    @Column(name = "new_data", nullable = true, length = -1)
    public String getNewData() {
        return newData;
    }

    public void setNewData(String newData) {
        this.newData = newData;
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
        AcceptedTempLog that = (AcceptedTempLog) o;
        return id == that.id &&
                Objects.equal(acceptedTempId, that.acceptedTempId) &&
                Objects.equal(createPerson, that.createPerson) &&
                Objects.equal(createTime, that.createTime) &&
                Objects.equal(oldData, that.oldData) &&
                Objects.equal(newData, that.newData) &&
                Objects.equal(state, that.state) &&
                Objects.equal(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, acceptedTempId, createPerson, createTime, oldData, newData, state, content);
    }
}

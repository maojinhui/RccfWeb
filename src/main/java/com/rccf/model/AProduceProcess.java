package com.rccf.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "a_produce_process", schema = "rccf", catalog = "")
public class AProduceProcess {
    private int id;
    private Integer produceId;
    private Integer adminPerson;
    private Timestamp adminTime;
    private String oldContent;
    private String newContent;

    @Id
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
    @Column(name = "admin_person")
    public Integer getAdminPerson() {
        return adminPerson;
    }

    public void setAdminPerson(Integer adminPerson) {
        this.adminPerson = adminPerson;
    }

    @Basic
    @Column(name = "admin_time")
    public Timestamp getAdminTime() {
        return adminTime;
    }

    public void setAdminTime(Timestamp adminTime) {
        this.adminTime = adminTime;
    }

    @Basic
    @Column(name = "old_content")
    public String getOldContent() {
        return oldContent;
    }

    public void setOldContent(String oldContent) {
        this.oldContent = oldContent;
    }

    @Basic
    @Column(name = "new_content")
    public String getNewContent() {
        return newContent;
    }

    public void setNewContent(String newContent) {
        this.newContent = newContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AProduceProcess that = (AProduceProcess) o;

        if (id != that.id) return false;
        if (produceId != null ? !produceId.equals(that.produceId) : that.produceId != null) return false;
        if (adminPerson != null ? !adminPerson.equals(that.adminPerson) : that.adminPerson != null) return false;
        if (adminTime != null ? !adminTime.equals(that.adminTime) : that.adminTime != null) return false;
        if (oldContent != null ? !oldContent.equals(that.oldContent) : that.oldContent != null) return false;
        if (newContent != null ? !newContent.equals(that.newContent) : that.newContent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (produceId != null ? produceId.hashCode() : 0);
        result = 31 * result + (adminPerson != null ? adminPerson.hashCode() : 0);
        result = 31 * result + (adminTime != null ? adminTime.hashCode() : 0);
        result = 31 * result + (oldContent != null ? oldContent.hashCode() : 0);
        result = 31 * result + (newContent != null ? newContent.hashCode() : 0);
        return result;
    }
}

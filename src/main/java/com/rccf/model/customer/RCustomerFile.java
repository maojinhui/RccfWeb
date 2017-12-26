package com.rccf.model.customer;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "r_customer_file", schema = "rccf", catalog = "")
public class RCustomerFile {
    private int id;
    private String customerId;
    private String file;
    private String thumb;
    private Integer type;
    private Timestamp createTime;
    private Integer createPerson;
    private String url;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "customer_id", nullable = true, length = 32)
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "file", nullable = true, length = 255)
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Basic
    @Column(name = "thumb", nullable = true, length = 255)
    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RCustomerFile that = (RCustomerFile) o;
        return id == that.id &&
                Objects.equal(customerId, that.customerId) &&
                Objects.equal(file, that.file) &&
                Objects.equal(thumb, that.thumb) &&
                Objects.equal(type, that.type) &&
                Objects.equal(createTime, that.createTime) &&
                Objects.equal(createPerson, that.createPerson);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, customerId, file, thumb, type, createTime, createPerson);
    }

    @Basic
    @Column(name = "url", nullable = true, length = 255)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

package com.rccf.model.poster;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "b_poster", schema = "rccf", catalog = "")
public class BPoster {
    private int id;
    private String title;
    private String backimg;
    private Timestamp addTime;
    private Integer addPerson;
    private Integer type;
    private Integer collections;
    private String extra;
    private String thumb;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 225)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "backimg", nullable = true, length = 225)
    public String getBackimg() {
        return backimg;
    }

    public void setBackimg(String backimg) {
        this.backimg = backimg;
    }

    @Basic
    @Column(name = "add_time", nullable = true)
    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    @Basic
    @Column(name = "add_person", nullable = true)
    public Integer getAddPerson() {
        return addPerson;
    }

    public void setAddPerson(Integer addPerson) {
        this.addPerson = addPerson;
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
    @Column(name = "collections", nullable = true)
    public Integer getCollections() {
        return collections;
    }

    public void setCollections(Integer collections) {
        this.collections = collections;
    }


    @Basic
    @Column(name = "extra", nullable = true, length = -1)
    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }



    @Basic
    @Column(name = "thumb", nullable = true, length = 225)
    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BPoster bPoster = (BPoster) o;
        return id == bPoster.id &&
                Objects.equal(title, bPoster.title) &&
                Objects.equal(backimg, bPoster.backimg) &&
                Objects.equal(addTime, bPoster.addTime) &&
                Objects.equal(addPerson, bPoster.addPerson) &&
                Objects.equal(type, bPoster.type) &&
                Objects.equal(collections, bPoster.collections) &&
                Objects.equal(extra, bPoster.extra) &&
                Objects.equal(thumb, bPoster.thumb);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, title, backimg, addTime, addPerson, type, collections, extra, thumb);
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("title", title)
                .add("backimg", backimg)
                .add("addTime", addTime)
                .add("addPerson", addPerson)
                .add("type", type)
                .add("collections", collections)
                .add("extra", extra)
                .add("thumb", thumb)
                .toString();
    }
}


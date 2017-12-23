package com.rccf.model.poster;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
@Table(name = "b_poster_font", schema = "rccf", catalog = "")
public class BPosterFont {
    private int id;
    private String name;
    private String director;
    private String extra;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "director", nullable = true, length = 255)
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Basic
    @Column(name = "extra", nullable = true, length = -1)
    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BPosterFont that = (BPosterFont) o;
        return id == that.id &&
                Objects.equal(name, that.name) &&
                Objects.equal(director, that.director) &&
                Objects.equal(extra, that.extra);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, director, extra);
    }
}

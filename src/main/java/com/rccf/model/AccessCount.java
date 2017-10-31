package com.rccf.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "access_count", schema = "rccf", catalog = "")
public class AccessCount {
    private int id;
    private String url;
    private String host;
    private Timestamp time;
    private String paramter;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "host")
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "paramter")
    public String getParamter() {
        return paramter;
    }

    public void setParamter(String paramter) {
        this.paramter = paramter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccessCount that = (AccessCount) o;

        if (id != that.id) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (paramter != null ? !paramter.equals(that.paramter) : that.paramter != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (paramter != null ? paramter.hashCode() : 0);
        return result;
    }
}

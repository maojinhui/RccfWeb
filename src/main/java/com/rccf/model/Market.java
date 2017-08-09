package com.rccf.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Market {
    private int id;
    private String marketImg;
    private String marketUrl;
    private String marketDes;
    private Byte state;
    private String marketTitle;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "market_img")
    public String getMarketImg() {
        return marketImg;
    }

    public void setMarketImg(String marketImg) {
        this.marketImg = marketImg;
    }

    @Basic
    @Column(name = "market_url")
    public String getMarketUrl() {
        return marketUrl;
    }

    public void setMarketUrl(String marketUrl) {
        this.marketUrl = marketUrl;
    }

    @Basic
    @Column(name = "market_des")
    public String getMarketDes() {
        return marketDes;
    }

    public void setMarketDes(String marketDes) {
        this.marketDes = marketDes;
    }

    @Basic
    @Column(name = "state")
    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Market market = (Market) o;

        if (id != market.id) return false;
        if (marketImg != null ? !marketImg.equals(market.marketImg) : market.marketImg != null) return false;
        if (marketUrl != null ? !marketUrl.equals(market.marketUrl) : market.marketUrl != null) return false;
        if (marketDes != null ? !marketDes.equals(market.marketDes) : market.marketDes != null) return false;
        if (state != null ? !state.equals(market.state) : market.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (marketImg != null ? marketImg.hashCode() : 0);
        result = 31 * result + (marketUrl != null ? marketUrl.hashCode() : 0);
        result = 31 * result + (marketDes != null ? marketDes.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }


    @Basic
    @Column(name = "market_title")
    public String getMarketTitle() {
        return marketTitle;
    }

    public void setMarketTitle(String marketTitle) {
        this.marketTitle = marketTitle;
    }
}

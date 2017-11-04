package com.rccf.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "r_customer_house", schema = "rccf", catalog = "")
public class RCustomerHouse {
    private int id;
    private String customerId;
    private String houseType;
    private String houseAddress;
    private String houseArea;
    private Date housePaytime;
    private Integer housePrice;
    private Integer houseIsMortgage;
    private Integer houseMortgageAmount;
    private Double houseMonthSupply;
    private Integer houseIsDiya;
    private Integer houseDiyaAmount;
    private String housePropertyRights;
    private String houseAltogether;
    private String houseUseSituation;
    private Integer houseYearRent;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "customer_id")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "house_type")
    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    @Basic
    @Column(name = "house_address")
    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    @Basic
    @Column(name = "house_area")
    public String getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(String houseArea) {
        this.houseArea = houseArea;
    }

    @Basic
    @Column(name = "house_paytime")
    public Date getHousePaytime() {
        return housePaytime;
    }

    public void setHousePaytime(Date housePaytime) {
        this.housePaytime = housePaytime;
    }

    @Basic
    @Column(name = "house_price")
    public Integer getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(Integer housePrice) {
        this.housePrice = housePrice;
    }

    @Basic
    @Column(name = "house_is_mortgage")
    public Integer getHouseIsMortgage() {
        return houseIsMortgage;
    }

    public void setHouseIsMortgage(Integer houseIsMortgage) {
        this.houseIsMortgage = houseIsMortgage;
    }

    @Basic
    @Column(name = "house_mortgage_amount")
    public Integer getHouseMortgageAmount() {
        return houseMortgageAmount;
    }

    public void setHouseMortgageAmount(Integer houseMortgageAmount) {
        this.houseMortgageAmount = houseMortgageAmount;
    }

    @Basic
    @Column(name = "house_month_supply")
    public Double getHouseMonthSupply() {
        return houseMonthSupply;
    }

    public void setHouseMonthSupply(Double houseMonthSupply) {
        this.houseMonthSupply = houseMonthSupply;
    }

    @Basic
    @Column(name = "house_is_diya")
    public Integer getHouseIsDiya() {
        return houseIsDiya;
    }

    public void setHouseIsDiya(Integer houseIsDiya) {
        this.houseIsDiya = houseIsDiya;
    }

    @Basic
    @Column(name = "house_diya_amount")
    public Integer getHouseDiyaAmount() {
        return houseDiyaAmount;
    }

    public void setHouseDiyaAmount(Integer houseDiyaAmount) {
        this.houseDiyaAmount = houseDiyaAmount;
    }

    @Basic
    @Column(name = "house_property_rights")
    public String getHousePropertyRights() {
        return housePropertyRights;
    }

    public void setHousePropertyRights(String housePropertyRights) {
        this.housePropertyRights = housePropertyRights;
    }

    @Basic
    @Column(name = "house_altogether")
    public String getHouseAltogether() {
        return houseAltogether;
    }

    public void setHouseAltogether(String houseAltogether) {
        this.houseAltogether = houseAltogether;
    }

    @Basic
    @Column(name = "house_use_situation")
    public String getHouseUseSituation() {
        return houseUseSituation;
    }

    public void setHouseUseSituation(String houseUseSituation) {
        this.houseUseSituation = houseUseSituation;
    }

    @Basic
    @Column(name = "house_year_rent")
    public Integer getHouseYearRent() {
        return houseYearRent;
    }

    public void setHouseYearRent(Integer houseYearRent) {
        this.houseYearRent = houseYearRent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RCustomerHouse that = (RCustomerHouse) o;

        if (id != that.id) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (houseType != null ? !houseType.equals(that.houseType) : that.houseType != null) return false;
        if (houseAddress != null ? !houseAddress.equals(that.houseAddress) : that.houseAddress != null) return false;
        if (houseArea != null ? !houseArea.equals(that.houseArea) : that.houseArea != null) return false;
        if (housePaytime != null ? !housePaytime.equals(that.housePaytime) : that.housePaytime != null) return false;
        if (housePrice != null ? !housePrice.equals(that.housePrice) : that.housePrice != null) return false;
        if (houseIsMortgage != null ? !houseIsMortgage.equals(that.houseIsMortgage) : that.houseIsMortgage != null)
            return false;
        if (houseMortgageAmount != null ? !houseMortgageAmount.equals(that.houseMortgageAmount) : that.houseMortgageAmount != null)
            return false;
        if (houseMonthSupply != null ? !houseMonthSupply.equals(that.houseMonthSupply) : that.houseMonthSupply != null)
            return false;
        if (houseIsDiya != null ? !houseIsDiya.equals(that.houseIsDiya) : that.houseIsDiya != null) return false;
        if (houseDiyaAmount != null ? !houseDiyaAmount.equals(that.houseDiyaAmount) : that.houseDiyaAmount != null)
            return false;
        if (housePropertyRights != null ? !housePropertyRights.equals(that.housePropertyRights) : that.housePropertyRights != null)
            return false;
        if (houseAltogether != null ? !houseAltogether.equals(that.houseAltogether) : that.houseAltogether != null)
            return false;
        if (houseUseSituation != null ? !houseUseSituation.equals(that.houseUseSituation) : that.houseUseSituation != null)
            return false;
        if (houseYearRent != null ? !houseYearRent.equals(that.houseYearRent) : that.houseYearRent != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (houseType != null ? houseType.hashCode() : 0);
        result = 31 * result + (houseAddress != null ? houseAddress.hashCode() : 0);
        result = 31 * result + (houseArea != null ? houseArea.hashCode() : 0);
        result = 31 * result + (housePaytime != null ? housePaytime.hashCode() : 0);
        result = 31 * result + (housePrice != null ? housePrice.hashCode() : 0);
        result = 31 * result + (houseIsMortgage != null ? houseIsMortgage.hashCode() : 0);
        result = 31 * result + (houseMortgageAmount != null ? houseMortgageAmount.hashCode() : 0);
        result = 31 * result + (houseMonthSupply != null ? houseMonthSupply.hashCode() : 0);
        result = 31 * result + (houseIsDiya != null ? houseIsDiya.hashCode() : 0);
        result = 31 * result + (houseDiyaAmount != null ? houseDiyaAmount.hashCode() : 0);
        result = 31 * result + (housePropertyRights != null ? housePropertyRights.hashCode() : 0);
        result = 31 * result + (houseAltogether != null ? houseAltogether.hashCode() : 0);
        result = 31 * result + (houseUseSituation != null ? houseUseSituation.hashCode() : 0);
        result = 31 * result + (houseYearRent != null ? houseYearRent.hashCode() : 0);
        return result;
    }
}

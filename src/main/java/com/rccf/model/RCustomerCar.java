package com.rccf.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "r_customer_car", schema = "rccf", catalog = "")
public class RCustomerCar {
    private int id;
    private String customerId;
    private String carBrand;
    private String carModel;
    private String carNumebrPlate;
    private Double carDirveDistance;
    private Date carBuyTime;
    private Double carBuyPrice;
    private Integer carIsMortgage;
    private Double carMortgageAmount;
    private Double carMonthApply;
    private Integer carIsDiya;
    private Double carDiyaAmount;

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
    @Column(name = "car_brand")
    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    @Basic
    @Column(name = "car_model")
    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    @Basic
    @Column(name = "car_numebr_plate")
    public String getCarNumebrPlate() {
        return carNumebrPlate;
    }

    public void setCarNumebrPlate(String carNumebrPlate) {
        this.carNumebrPlate = carNumebrPlate;
    }

    @Basic
    @Column(name = "car_dirve_distance")
    public Double getCarDirveDistance() {
        return carDirveDistance;
    }

    public void setCarDirveDistance(Double carDirveDistance) {
        this.carDirveDistance = carDirveDistance;
    }

    @Basic
    @Column(name = "car_buy_time")
    public Date getCarBuyTime() {
        return carBuyTime;
    }

    public void setCarBuyTime(Date carBuyTime) {
        this.carBuyTime = carBuyTime;
    }

    @Basic
    @Column(name = "car_buy_price")
    public Double getCarBuyPrice() {
        return carBuyPrice;
    }

    public void setCarBuyPrice(Double carBuyPrice) {
        this.carBuyPrice = carBuyPrice;
    }

    @Basic
    @Column(name = "car_is_mortgage")
    public Integer getCarIsMortgage() {
        return carIsMortgage;
    }

    public void setCarIsMortgage(Integer carIsMortgage) {
        this.carIsMortgage = carIsMortgage;
    }

    @Basic
    @Column(name = "car_mortgage_amount")
    public Double getCarMortgageAmount() {
        return carMortgageAmount;
    }

    public void setCarMortgageAmount(Double carMortgageAmount) {
        this.carMortgageAmount = carMortgageAmount;
    }

    @Basic
    @Column(name = "car_month_apply")
    public Double getCarMonthApply() {
        return carMonthApply;
    }

    public void setCarMonthApply(Double carMonthApply) {
        this.carMonthApply = carMonthApply;
    }

    @Basic
    @Column(name = "car_is_diya")
    public Integer getCarIsDiya() {
        return carIsDiya;
    }

    public void setCarIsDiya(Integer carIsDiya) {
        this.carIsDiya = carIsDiya;
    }

    @Basic
    @Column(name = "car_diya_amount")
    public Double getCarDiyaAmount() {
        return carDiyaAmount;
    }

    public void setCarDiyaAmount(Double carDiyaAmount) {
        this.carDiyaAmount = carDiyaAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RCustomerCar that = (RCustomerCar) o;

        if (id != that.id) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (carBrand != null ? !carBrand.equals(that.carBrand) : that.carBrand != null) return false;
        if (carModel != null ? !carModel.equals(that.carModel) : that.carModel != null) return false;
        if (carNumebrPlate != null ? !carNumebrPlate.equals(that.carNumebrPlate) : that.carNumebrPlate != null)
            return false;
        if (carDirveDistance != null ? !carDirveDistance.equals(that.carDirveDistance) : that.carDirveDistance != null)
            return false;
        if (carBuyTime != null ? !carBuyTime.equals(that.carBuyTime) : that.carBuyTime != null) return false;
        if (carBuyPrice != null ? !carBuyPrice.equals(that.carBuyPrice) : that.carBuyPrice != null) return false;
        if (carIsMortgage != null ? !carIsMortgage.equals(that.carIsMortgage) : that.carIsMortgage != null)
            return false;
        if (carMortgageAmount != null ? !carMortgageAmount.equals(that.carMortgageAmount) : that.carMortgageAmount != null)
            return false;
        if (carMonthApply != null ? !carMonthApply.equals(that.carMonthApply) : that.carMonthApply != null)
            return false;
        if (carIsDiya != null ? !carIsDiya.equals(that.carIsDiya) : that.carIsDiya != null) return false;
        if (carDiyaAmount != null ? !carDiyaAmount.equals(that.carDiyaAmount) : that.carDiyaAmount != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (carBrand != null ? carBrand.hashCode() : 0);
        result = 31 * result + (carModel != null ? carModel.hashCode() : 0);
        result = 31 * result + (carNumebrPlate != null ? carNumebrPlate.hashCode() : 0);
        result = 31 * result + (carDirveDistance != null ? carDirveDistance.hashCode() : 0);
        result = 31 * result + (carBuyTime != null ? carBuyTime.hashCode() : 0);
        result = 31 * result + (carBuyPrice != null ? carBuyPrice.hashCode() : 0);
        result = 31 * result + (carIsMortgage != null ? carIsMortgage.hashCode() : 0);
        result = 31 * result + (carMortgageAmount != null ? carMortgageAmount.hashCode() : 0);
        result = 31 * result + (carMonthApply != null ? carMonthApply.hashCode() : 0);
        result = 31 * result + (carIsDiya != null ? carIsDiya.hashCode() : 0);
        result = 31 * result + (carDiyaAmount != null ? carDiyaAmount.hashCode() : 0);
        return result;
    }
}

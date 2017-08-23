package com.rccf.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "bank_loan_rate", schema = "rccf", catalog = "")
public class BankLoanRate {
    private int id;
    private Double rateOne;
    private Double rateOneFive;
    private Double rateOverFive;
    private Timestamp time;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "rate_one")
    public Double getRateOne() {
        return rateOne;
    }

    public void setRateOne(Double rateOne) {
        this.rateOne = rateOne;
    }

    @Basic
    @Column(name = "rate_one_five")
    public Double getRateOneFive() {
        return rateOneFive;
    }

    public void setRateOneFive(Double rateOneFive) {
        this.rateOneFive = rateOneFive;
    }

    @Basic
    @Column(name = "rate_over_five")
    public Double getRateOverFive() {
        return rateOverFive;
    }

    public void setRateOverFive(Double rateOverFive) {
        this.rateOverFive = rateOverFive;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankLoanRate that = (BankLoanRate) o;

        if (id != that.id) return false;
        if (rateOne != null ? !rateOne.equals(that.rateOne) : that.rateOne != null) return false;
        if (rateOneFive != null ? !rateOneFive.equals(that.rateOneFive) : that.rateOneFive != null) return false;
        if (rateOverFive != null ? !rateOverFive.equals(that.rateOverFive) : that.rateOverFive != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (rateOne != null ? rateOne.hashCode() : 0);
        result = 31 * result + (rateOneFive != null ? rateOneFive.hashCode() : 0);
        result = 31 * result + (rateOverFive != null ? rateOverFive.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}

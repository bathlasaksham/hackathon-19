package com.airbus.hackathon.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "coupons")
public class Coupon extends AbstractEntity<Integer> implements Serializable {

    private String couponId;

    private Integer discount;

    private Integer maxDiscount;

    public Coupon() {
    }

    public Coupon(String couponId, Integer discount, Integer max_discount) {
        this.couponId = couponId;
        this.discount = discount;
        this.maxDiscount = max_discount;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(Integer maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "couponId='" + couponId + '\'' +
                ", discount=" + discount +
                ", maxDiscount=" + maxDiscount +
                '}';
    }
    
}

package com.airbus.hackathon.repo;

import com.airbus.hackathon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Integer> {


    List<Coupon> findByCouponId(String coupon);
}

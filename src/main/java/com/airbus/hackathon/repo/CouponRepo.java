package com.airbus.hackathon.repo;

import com.airbus.hackathon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {
}

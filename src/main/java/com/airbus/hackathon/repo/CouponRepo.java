package com.airbus.hackathon.repo;

import com.airbus.hackathon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Integer> {
}

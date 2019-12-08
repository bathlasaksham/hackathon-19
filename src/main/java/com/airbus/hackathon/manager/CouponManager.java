package com.airbus.hackathon.manager;

import com.airbus.hackathon.entity.Coupon;
import com.airbus.hackathon.repo.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class CouponManager extends AbstractManager<Coupon> {

    @Autowired
    private CouponRepo couponRepo;

    @Override
    public JpaRepository<Coupon, Integer> getRepo() {
        return couponRepo;
    }


}

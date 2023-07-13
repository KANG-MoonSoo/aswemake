package com.example.aswemake.service;

import com.example.aswemake.entity.Coupon;
import com.example.aswemake.repository.CouponRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;

    public Coupon createCoupon(Coupon coupon){
        return couponRepository.save(coupon);
    }
}

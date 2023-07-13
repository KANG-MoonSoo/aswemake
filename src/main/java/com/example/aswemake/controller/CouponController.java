package com.example.aswemake.controller;

import com.example.aswemake.dto.CouponDto;
import com.example.aswemake.entity.Coupon;
import com.example.aswemake.mapper.CouponMapper;
import com.example.aswemake.service.CouponService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/coupon")
@Validated
@AllArgsConstructor
public class CouponController {
    private final CouponMapper couponMapper;
    private final CouponService couponService;

    @PostMapping
    public ResponseEntity postCoupon(@Valid @RequestBody CouponDto.CouponPostDto couponPostDto){
        Coupon coupon = couponService.createCoupon(couponMapper.couponPostDtoToCoupon(couponPostDto));
        return new ResponseEntity<>(couponMapper.couponToCouponResponseDto(coupon), HttpStatus.CREATED);
    }
}

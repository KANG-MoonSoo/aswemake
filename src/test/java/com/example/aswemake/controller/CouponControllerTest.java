package com.example.aswemake.controller;

import com.example.aswemake.dto.CouponDto;
import com.example.aswemake.entity.Coupon;
import com.example.aswemake.mapper.CouponMapper;
import com.example.aswemake.service.CouponService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CouponControllerTest {
    @Mock
    private CouponMapper couponMapper;
    @Mock
    private CouponService couponService;
    @InjectMocks
    private CouponController couponController;

    @Test
    public void testPostCoupon() {
        MockitoAnnotations.openMocks(this);

        CouponDto.CouponPostDto couponPostDto = new CouponDto.CouponPostDto();
        couponPostDto.setType("Type");
        couponPostDto.setRange("Range");
        couponPostDto.setValue(0.7);

        Coupon coupon = new Coupon();
        coupon.setCouponId(1);
        coupon.setType("Type");
        coupon.setRange("Range");
        coupon.setValue(0.7);

        CouponDto.CouponResponseDto couponResponseDto = new CouponDto.CouponResponseDto();
        couponResponseDto.setCouponId(1);
        couponResponseDto.setType("Type");
        couponResponseDto.setRange("Range");
        couponResponseDto.setValue(0.7);

        when(couponMapper.couponPostDtoToCoupon(couponPostDto)).thenReturn(coupon);
        when(couponService.createCoupon(coupon)).thenReturn(coupon);
        when(couponMapper.couponToCouponResponseDto(coupon)).thenReturn(couponResponseDto);

        ResponseEntity response = couponController.postCoupon(couponPostDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(couponResponseDto, response.getBody());

        verify(couponMapper).couponPostDtoToCoupon(couponPostDto);
        verify(couponService).createCoupon(coupon);
        verify(couponMapper).couponToCouponResponseDto(coupon);
    }
}
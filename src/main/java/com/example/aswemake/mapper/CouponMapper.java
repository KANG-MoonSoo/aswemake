package com.example.aswemake.mapper;

import com.example.aswemake.dto.CouponDto;
import com.example.aswemake.entity.Coupon;
import org.mapstruct.Mapper;

import java.text.spi.CollatorProvider;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    default Coupon couponPostDtoToCoupon(CouponDto.CouponPostDto couponPostDto){
        Coupon coupon = new Coupon();
        coupon.setType(couponPostDto.getType());
        coupon.setRange(couponPostDto.getRange());
        coupon.setValue(couponPostDto.getValue());

        return coupon;
    }
    default CouponDto.CouponResponseDto couponToCouponResponseDto(Coupon coupon){
        CouponDto.CouponResponseDto couponResponseDto = new CouponDto.CouponResponseDto();

        couponResponseDto.setCouponId(coupon.getCouponId());
        couponResponseDto.setType(coupon.getType());
        couponResponseDto.setRange(coupon.getRange());
        couponResponseDto.setValue(coupon.getValue());

        return couponResponseDto;

    }
}

package com.example.aswemake.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

public class CouponDto {
    @Getter
    public static class CouponPostDto{
        private String type;

        private String range;

        private double value;
        @Nullable
        private long productId;

    }
    @Getter
    @Setter
    public static class CouponResponseDto{
        private long couponId;

        private String type;

        private String range;

        private double value;

        private long productId;

    }
}

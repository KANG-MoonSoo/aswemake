package com.example.aswemake.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class OrderDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderPostDto{
        private List<BasketDto.BasketPostDto> basket;
        private int deliveryFee;
    }
    @Getter
    @Setter
    public static class OrderResponseDto{
        private long orderId;
        private List<BasketDto.BasketResponseDto> basket;
        private int deliveryFee;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CouponApplicationOrderGetDto{
        private long orderId;
        private long couponId;
    }
    @Setter
    @Getter
    @NoArgsConstructor
    public static class CouponApplicationOrderResponseDto{
        private int response;
    }


    @Setter
    @Getter
    public static class getBasketResponse{
        private long productId;
        private String productName;
        private int price;
        private int quantity;
    }
    @Getter
    @Setter
    public static class TotalPricePostDto{
        private long orderId;
    }
    @Getter
    @Setter
    public static class TotalPriceResponseDto{
        private int totalPrice;
    }
}

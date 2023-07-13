package com.example.aswemake.dto;

import lombok.Getter;
import lombok.Setter;

public class BasketDto {
    @Getter
    public static class BasketPostDto{
        private long productId;
        private int quantity;
    }
    @Getter
    @Setter
    public static class BasketResponseDto{
        private long productId;
        private int quantity;
        private String productName;
        private int price;
    }
}

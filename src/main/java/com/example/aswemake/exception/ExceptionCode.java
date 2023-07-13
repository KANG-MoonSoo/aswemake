package com.example.aswemake.exception;

import lombok.Getter;

public enum ExceptionCode {
    ORDER_NOT_FOUND(404, "Order not found"),
    BASKET_NOT_FOUND(404, "Basket not found"),
    PRODUCT_NOT_FOUND(404, "Product not found");



    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message){
        this.status = status;
        this.message = message;
    }
}

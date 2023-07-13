package com.example.aswemake.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductTimeDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductTimeGetDto{
        @NotBlank(message = "The name must bot be blank.")
        private String productName;
        @NotNull
        private String time;
    }
    @Setter
    @Getter
    @NoArgsConstructor
    public static class ProductTimeResponseDto{
        private String response;

    }
}

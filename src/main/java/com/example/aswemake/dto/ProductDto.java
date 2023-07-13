package com.example.aswemake.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

public class ProductDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductPostDto{
        @NotBlank(message = "The name must not be blank.")
        private String productName;
        @NotNull
        private int price;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ProductPatchDto{
        private long productId;

        @NotBlank(message = "The name must not be blank.")
        private String productName;

        @NotNull
        private int price;
    }
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

    @Getter
    @Setter
    public static class ProductPatchListDto{
        private List<ProductPatchDto> ProductList;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class ProductResponseDto{
        private long productId;

        private String productName;

        private int price;
    }
}

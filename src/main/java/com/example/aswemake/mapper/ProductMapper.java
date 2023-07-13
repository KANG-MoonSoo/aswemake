package com.example.aswemake.mapper;

import com.example.aswemake.dto.ProductDto;
import com.example.aswemake.entity.Product;
import com.example.aswemake.entity.ProductTime;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    default Product productPostDtoToProduct(ProductDto.ProductPostDto productPostDto){
        Product product = new Product();
        product.setProductName(productPostDto.getProductName());
        product.setPrice(productPostDto.getPrice());

        return product;
    }
    default ProductTime productPostDtoToProductTime(ProductDto.ProductPostDto productPostDto){
        ProductTime productTime = new ProductTime();
        productTime.setProductName(productPostDto.getProductName());
        productTime.setPrice(productPostDto.getPrice());
        productTime.setTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return productTime;
    }
    default ProductTime productPatchDtoToProductTime(ProductDto.ProductPatchDto productPatchDto){
        ProductTime productTime = new ProductTime();
        productTime.setProductName(productPatchDto.getProductName());
        productTime.setPrice(productPatchDto.getPrice());
        productTime.setTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return productTime;
    }
    Product productPatchDtoToProduct(ProductDto.ProductPatchDto productPatchDto);
    default ProductDto.ProductResponseDto productToProductResponseDto(Product product){
        ProductDto.ProductResponseDto productResponseDto = new ProductDto.ProductResponseDto();

        productResponseDto.setProductId(product.getProductId());
        productResponseDto.setProductName(product.getProductName());
        productResponseDto.setPrice(product.getPrice());

        return productResponseDto;
    }
    default List<ProductDto.ProductResponseDto> productsToProductResponseDtos(List<Product> products){
        if (products == null){
            return null;
        }
        List<ProductDto.ProductResponseDto> list = new ArrayList<>(products.size());
        for (Product product : products){
            ProductDto.ProductResponseDto productResponseDto = new ProductDto.ProductResponseDto();
            productResponseDto.setProductId(product.getProductId());
            productResponseDto.setProductName(product.getProductName());
            productResponseDto.setPrice(product.getPrice());
            list.add(productResponseDto);
        }
        return list;
    }
}

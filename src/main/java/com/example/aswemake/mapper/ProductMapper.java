package com.example.aswemake.mapper;

import com.example.aswemake.dto.ProductDto;
import com.example.aswemake.entity.Product;
import org.mapstruct.Mapper;

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

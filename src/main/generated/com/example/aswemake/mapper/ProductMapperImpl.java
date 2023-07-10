package com.example.aswemake.mapper;

import com.example.aswemake.dto.ProductDto;
import com.example.aswemake.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-10T22:10:42+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product productPatchDtoToProduct(ProductDto.ProductPatchDto productPatchDto) {
        if ( productPatchDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setProductId( productPatchDto.getProductId() );
        product.setProductName( productPatchDto.getProductName() );
        product.setPrice( productPatchDto.getPrice() );

        return product;
    }
}

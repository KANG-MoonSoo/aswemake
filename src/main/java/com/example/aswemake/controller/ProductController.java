package com.example.aswemake.controller;

import com.example.aswemake.dto.ProductDto;
import com.example.aswemake.entity.Product;
import com.example.aswemake.entity.ProductTime;
import com.example.aswemake.mapper.ProductMapper;
import com.example.aswemake.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/product")
@Validated
@Slf4j
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    //상품 등록
    @PostMapping
    public ResponseEntity postProduct(@Valid @RequestBody ProductDto.ProductPostDto productPostDto){
        Product product = productService.createProduct(productMapper.productPostDtoToProduct(productPostDto));
        ProductTime productTime = productService.createProductTime(productMapper.productPostDtoToProductTime(productPostDto));

        return new ResponseEntity<>(productMapper.productToProductResponseDto(product), HttpStatus.CREATED);
    }

    // 상품 수정
    @PatchMapping("/{product-id}")
    public ResponseEntity patchProduct(@PathVariable("product-id") @Positive long productId,
                             @RequestBody @Valid ProductDto.ProductPatchDto productPatchDto){
        productPatchDto.setProductId(productId);

        Product response = productService.updateProduct(productMapper.productPatchDtoToProduct(productPatchDto));
        ProductTime productTime = productService.createProductTime(productMapper.productPatchDtoToProductTime(productPatchDto));

        return new ResponseEntity<>(productMapper.productToProductResponseDto(response), HttpStatus.OK);
    }
    // 특정 상품 조회
    @GetMapping("/{product-id}")
    public ResponseEntity getProduct(@PathVariable("product-id") @Positive long productId){
        Product product = productService.findProduct(productId);
        return new ResponseEntity<>(productMapper.productToProductResponseDto(product), HttpStatus.OK);
    }
    // 모든 상품 조회
    @GetMapping
    public ResponseEntity getProducts(){
        return new ResponseEntity<>(
                productMapper.productsToProductResponseDtos(productService.findProducts()), HttpStatus.OK);
    }
    // 상품 삭제
    @DeleteMapping("/{product-id}")
    public ResponseEntity deleteProduct(@PathVariable("product-id") @Positive long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/priceAtTime")
    public ResponseEntity getProductPriceAtTime(ProductDto.ProductTimeGetDto productTimeGetDto) {
        ProductDto.ProductTimeResponseDto response = productService.search(productTimeGetDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}


package com.example.aswemake.service;

import com.example.aswemake.dto.ProductDto;
import com.example.aswemake.entity.Product;
import com.example.aswemake.entity.ProductTime;
import com.example.aswemake.exception.BusinessLogicException;
import com.example.aswemake.exception.ExceptionCode;
import com.example.aswemake.repository.ProductRepository;
import com.example.aswemake.repository.ProductTimeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductTimeRepository productTimeRepository;

    //상품 생성
    public Product createProduct(Product product){
        return productRepository.save(product);
    }
    //상품 수정
    public Product updateProduct(Product product){
        Product findProduct = findVerifiedProduct(product.getProductId());

        Optional.ofNullable(product.getProductName())
                .ifPresent(productName -> findProduct.setProductName(productName));
        Optional.ofNullable(product.getPrice())
                .ifPresent(price ->  findProduct.setPrice(price));
        return productRepository.save(findProduct);
    }
    //특정 상품 조회
    public Product findProduct(long productId){
        return findVerifiedProduct(productId);
    }
    //모든 상품 조회
    public List<Product> findProducts(){
        return productRepository.findAll();
    }
    //상품 삭제
    public void deleteProduct(long productId){
        Product findProduct = findVerifiedProduct(productId);
        productRepository.delete(findProduct);
    }
    //있는 상품인지 확인
    public Product findVerifiedProduct(long productId){
        Optional<Product> optionalProduct = productRepository.findById(productId);

        Product findProduct = optionalProduct.orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.PRODUCT_NOT_FOUND));

        return findProduct;
    }
    public ProductTime createProductTime(ProductTime productTime){
        return productTimeRepository.save(productTime);
    }
    public ProductDto.ProductTimeResponseDto search(ProductDto.ProductTimeGetDto productTimeGetDto) {
        Optional<List<ProductTime>> findProductTimeList = productTimeRepository.findByProductName(productTimeGetDto.getProductName());

        if (!findProductTimeList.isPresent() || findProductTimeList.get().isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.PRODUCT_NOT_FOUND);
        }

        List<ProductTime> filteredProductTimeList = findProductTimeList.get().stream()
                .filter(pt -> pt.getTime().compareTo(productTimeGetDto.getTime()) <= 0)
                .collect(Collectors.toList());

        if (filteredProductTimeList.isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.PRODUCT_NOT_FOUND);
        }

        ProductTime latestProductTime = filteredProductTimeList.get(filteredProductTimeList.size() - 1);

        ProductDto.ProductTimeResponseDto response = new ProductDto.ProductTimeResponseDto();

        response.setResponse(
                productTimeGetDto.getTime() + " 시점의 " + productTimeGetDto.getProductName() + "상품의 가격 = " + latestProductTime.getPrice() + "원");

        return response;
    }

}

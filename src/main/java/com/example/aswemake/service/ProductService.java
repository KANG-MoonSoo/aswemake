package com.example.aswemake.service;

import com.example.aswemake.entity.Product;
import com.example.aswemake.exception.BusinessLogicException;
import com.example.aswemake.exception.ExceptionCode;
import com.example.aswemake.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
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
}

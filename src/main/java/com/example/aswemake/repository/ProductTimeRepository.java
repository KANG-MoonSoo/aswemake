package com.example.aswemake.repository;

import com.example.aswemake.entity.ProductTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductTimeRepository extends JpaRepository<ProductTime, Long> {
    Optional<List<ProductTime>> findByProductName(String productName);
}

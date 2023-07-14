package com.example.aswemake.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "product_time")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long historyId;

    private String productName;

    private int price;

    private String time;
}

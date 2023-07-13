package com.example.aswemake.controller;

import com.example.aswemake.dto.OrderDto;
import com.example.aswemake.entity.Order;
import com.example.aswemake.mapper.OrderMapper;
import com.example.aswemake.service.OrderService;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/order")
@Validated
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity postOrder(@Valid @RequestBody OrderDto.OrderPostDto orderPostDto){
        Order order = orderService.createOrder(orderMapper.orderPostDtoToOrder(orderPostDto));

        return new ResponseEntity<>(orderMapper.orderToOrderResponseDto(order), HttpStatus.CREATED);
    }
    @GetMapping("/totalPrice/{order-id}")
    public ResponseEntity totalPrice(@PathVariable("order-id") @Positive long orderId){
        int totalPrice = orderService.totalPrice(orderId);
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }
}

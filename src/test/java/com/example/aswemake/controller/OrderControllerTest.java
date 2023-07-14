package com.example.aswemake.controller;

import com.example.aswemake.dto.OrderDto;
import com.example.aswemake.entity.Order;
import com.example.aswemake.mapper.OrderMapper;
import com.example.aswemake.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderControllerTest {
    @Mock
    private OrderService orderService;
    @Mock
    private OrderMapper orderMapper;
    @InjectMocks
    private OrderController orderController;

    @Test
    public void testPostOrder() {
        MockitoAnnotations.openMocks(this);

        OrderDto.OrderPostDto orderPostDto = new OrderDto.OrderPostDto();

        Order order = new Order();

        OrderDto.OrderResponseDto orderResponseDto = new OrderDto.OrderResponseDto();

        when(orderMapper.orderPostDtoToOrder(orderPostDto)).thenReturn(order);
        when(orderService.createOrder(order)).thenReturn(order);
        when(orderMapper.orderToOrderResponseDto(order)).thenReturn(orderResponseDto);

        ResponseEntity response = orderController.postOrder(orderPostDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(orderResponseDto, response.getBody());

        verify(orderMapper).orderPostDtoToOrder(orderPostDto);
        verify(orderService).createOrder(order);
        verify(orderMapper).orderToOrderResponseDto(order);
    }

    @Test
    public void testTotalPrice() {
        MockitoAnnotations.openMocks(this);

        long orderId = 1;
        int totalPrice = 100;

        when(orderService.totalPrice(orderId)).thenReturn(totalPrice);

        ResponseEntity response = orderController.totalPrice(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(totalPrice, response.getBody());

        verify(orderService).totalPrice(orderId);
    }

    @Test
    public void testCouponApplicationPrice() {
        MockitoAnnotations.openMocks(this);

        OrderDto.CouponApplicationOrderGetDto couponApplicationOrderGetDto = new OrderDto.CouponApplicationOrderGetDto();

        OrderDto.CouponApplicationOrderResponseDto responseDto = new OrderDto.CouponApplicationOrderResponseDto();

        when(orderService.couponApplicationPrice(couponApplicationOrderGetDto)).thenReturn(responseDto);

        ResponseEntity response = orderController.couponApplicationPrice(couponApplicationOrderGetDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());

        verify(orderService).couponApplicationPrice(couponApplicationOrderGetDto);
    }
}

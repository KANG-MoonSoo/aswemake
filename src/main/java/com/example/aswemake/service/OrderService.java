package com.example.aswemake.service;

import com.example.aswemake.dto.OrderDto;
import com.example.aswemake.entity.Basket;
import com.example.aswemake.entity.Coupon;
import com.example.aswemake.entity.Order;
import com.example.aswemake.entity.Product;
import com.example.aswemake.exception.BusinessLogicException;
import com.example.aswemake.exception.ExceptionCode;
import com.example.aswemake.repository.BasketRepository;
import com.example.aswemake.repository.CouponRepository;
import com.example.aswemake.repository.OrderRepository;
import com.example.aswemake.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CouponRepository couponRepository;
    private final BasketRepository basketRepository;

    //주문 생성
    public Order createOrder(Order order){
        for (int i = 0; i < order.getBaskets().size(); i++) {
            Optional<Product> product = productRepository.findById(order.getBaskets().get(i).getProduct().getProductId());
            if (product.isPresent()){
                order.getBaskets().get(i).setProduct(product.get());
            }
        }
        return orderRepository.save(order);
    }
    // 토탈 주문 금액
    public int totalPrice(long orderId){
        int total = 0;
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()){
            for (int i = 0; i < order.get().getBaskets().size(); i++) {
                total += order.get().getBaskets().get(i).getProduct().getPrice() * order.get().getBaskets().get(i).getQuantity();
            }
            total += order.get().getDeliveryFee();
        }else {
            throw new BusinessLogicException(ExceptionCode.BASKET_NOT_FOUND);
        }
        return total;
    }
    //쿠폰 적용 금액
    public OrderDto.CouponApplicationOrderResponseDto couponApplicationPrice(OrderDto.CouponApplicationOrderGetDto couponApplicationOrderGetDto){
        Optional<Order> order = orderRepository.findById(couponApplicationOrderGetDto.getOrderId());
        Optional<Coupon> coupon = couponRepository.findById(couponApplicationOrderGetDto.getCouponId());
        int productPrice = totalPrice(couponApplicationOrderGetDto.getOrderId()) - order.get().getDeliveryFee();
        int result = 0;
        if (coupon.get().getType().equals("고정")){
            result = (int)(productPrice - coupon.get().getValue());
        }
        else if (coupon.get().getType().equals("비율")) {
            if (coupon.get().getRange().equals("주문 전체")){
                result = (int)(productPrice * coupon.get().getValue());
            }
            else if (coupon.get().getRange().equals("특정 상품 한정")) {
                for (int i = 0; i < order.get().getBaskets().size(); i++) {
                    if (order.get().getBaskets().get(i).getProduct().getProductId() == coupon.get().getProductId()){
                        result += order.get().getBaskets().get(i).getProduct().getPrice() *
                                coupon.get().getValue() *
                                order.get().getBaskets().get(i).getQuantity();
                    }else {
                        result += order.get().getBaskets().get(i).getProduct().getPrice() * order.get().getBaskets().get(i).getQuantity();
                    }
                }
            }
        }
        OrderDto.CouponApplicationOrderResponseDto response = new OrderDto.CouponApplicationOrderResponseDto();
        response.setResponse(result + order.get().getDeliveryFee());
        return response;
    }

}

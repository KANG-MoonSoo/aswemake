package com.example.aswemake.mapper;

import com.example.aswemake.dto.BasketDto;
import com.example.aswemake.dto.OrderDto;
import com.example.aswemake.dto.ProductDto;
import com.example.aswemake.entity.Basket;
import com.example.aswemake.entity.Order;
import com.example.aswemake.entity.Product;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    default Order orderPostDtoToOrder(OrderDto.OrderPostDto orderPostDto){
        Order order = new Order();
        List<Basket> basketList = new ArrayList<>();
        for (int i = 0; i < orderPostDto.getBasket().size(); i++) {
            Product product = new Product();
            product.setProductId(orderPostDto.getBasket().get(i).getProductId());

            Basket basket = new Basket();
            basket.setProduct(product);
            basket.setQuantity(orderPostDto.getBasket().get(i).getQuantity());
            basket.setOrder(order);
            basketList.add(basket);
        }
        order.setBaskets(basketList);
        order.setDeliveryFee(orderPostDto.getDeliveryFee());

        return order;
    }
    default OrderDto.OrderResponseDto orderToOrderResponseDto(Order order){
        OrderDto.OrderResponseDto response = new OrderDto.OrderResponseDto();
        List<Basket> basketList = order.getBaskets();

        response.setOrderId(order.getOrderId());
        response.setBasket(basketToBasketResponseDto(basketList));
        response.setDeliveryFee(order.getDeliveryFee());
        return response;
    }
    default List<BasketDto.BasketResponseDto> basketToBasketResponseDto(List<Basket> basketList){
        List<BasketDto.BasketResponseDto> responses = new ArrayList<>();
        for (int i = 0; i < basketList.size(); i++) {
            BasketDto.BasketResponseDto basketResponseDto = new BasketDto.BasketResponseDto();
            basketResponseDto.setProductId(basketList.get(i).getProduct().getProductId());
            basketResponseDto.setQuantity(basketList.get(i).getQuantity());
            basketResponseDto.setProductName(basketList.get(i).getProduct().getProductName());
            basketResponseDto.setPrice(basketList.get(i).getProduct().getPrice());
            responses.add(basketResponseDto);
        }
        return responses;
    }
}

package com.ecommerce.orderservice.controller.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record CreateOrderRequestDto(Long customerId, LocalDateTime orderDate,
                                    List<OrderItemDto> orderItemDtos) {

}

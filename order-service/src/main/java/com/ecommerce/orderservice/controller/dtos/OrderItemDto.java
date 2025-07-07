package com.ecommerce.orderservice.controller.dtos;

import java.math.BigDecimal;

public record OrderItemDto(Integer quantity, Long productId, String productName,
                           BigDecimal unitPrice) {

}

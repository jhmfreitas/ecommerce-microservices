package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.controller.dtos.CreateOrderRequestDto;
import com.ecommerce.orderservice.controller.dtos.OrderItemDto;
import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTest {

  @Autowired
  OrderService orderService;

  @Autowired
  TestRestTemplate testRestTemplate;

  @Autowired
  ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    OrderItemDto orderItemDto = new OrderItemDto(1, 1L, "Product Name", BigDecimal.valueOf(56.5));
    Stream.iterate(1, i -> i + 1).limit(10)
        .forEach(i -> {
          CreateOrderRequestDto createOrderRequestDto = new CreateOrderRequestDto(1L,
              LocalDateTime.now(),
              Stream.of(orderItemDto).toList());
          orderService.createOrder(createOrderRequestDto);
        });
  }

  @Test
  void getOrdersSuccess() throws JsonProcessingException {
    String jsonResponse = testRestTemplate.getForObject("/order", String.class);

    JsonNode root = objectMapper.readTree(jsonResponse);
    JsonNode content = root.get("content");

    Order[] orders = objectMapper.treeToValue(content, Order[].class);

    Assertions.assertNotNull(orders);
    Assertions.assertEquals(10, orders.length);
  }

}
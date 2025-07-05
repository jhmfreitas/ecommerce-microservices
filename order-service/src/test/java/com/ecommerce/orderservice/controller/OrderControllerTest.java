package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.model.OrderItem;
import com.ecommerce.orderservice.repository.OrderRepository;
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
  OrderRepository orderRepository;

  @Autowired
  TestRestTemplate testRestTemplate;

  @BeforeEach
  void setUp() {
    Stream.iterate(1, i -> i + 1).limit(10)
        .forEach(i -> orderRepository.save(createOrder(i)));
  }

  private static Order createOrder(Integer orderId) {
    OrderItem orderItem = new OrderItem(1, 1L, "Product Name", BigDecimal.valueOf(56.5));
    Order order = new Order(Long.valueOf(orderId), LocalDateTime.now());
    order.addItem(orderItem);
    return order;
  }

  @Test
  void getOrdersSuccess() {
    Order[] orders = testRestTemplate.getForObject("/order", Order[].class);

    Assertions.assertNotNull(orders);
    Assertions.assertEquals(10, orders.length);
  }

}
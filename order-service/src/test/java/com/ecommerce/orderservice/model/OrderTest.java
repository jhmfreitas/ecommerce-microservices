package com.ecommerce.orderservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;

class OrderTest {

  @Test
  void constructor_should_set_customer_id_and_order_date() {
    Long customerId = 42L;
    LocalDateTime now = LocalDateTime.now();

    Order order = new Order(customerId, now);

    assertEquals(customerId, order.getCustomerId());
    assertEquals(now, order.getOrderDate());
    assertNotNull(order.getItems());
    assertTrue(order.getItems().isEmpty());
  }

  @Test
  void add_item_should_add_order_item_to_order() {
    Order order = new Order(1L, LocalDateTime.now());

    OrderItem item = new OrderItem(2, 10L, "Test Product", BigDecimal.valueOf(25.50));
    order.addItem(item);

    List<OrderItem> items = order.getItems();

    assertEquals(1, items.size());
    assertSame(item, items.getFirst());
  }

  @Test
  void get_id_should_return_null_before_persisted() {
    Order order = new Order(1L, LocalDateTime.now());
    assertNull(order.getId(), "ID should be null before persistence");
  }

}
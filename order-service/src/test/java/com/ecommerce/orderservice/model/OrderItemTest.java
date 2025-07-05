package com.ecommerce.orderservice.model;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderItemTest {

  static final String productName = "Product Name";
  static final long productId = 1L;
  static final int quantity = 1;
  static final int invalidQuantity = 0;
  static final BigDecimal unitPrice = BigDecimal.valueOf(56.5);
  static final BigDecimal negativeUnitPrice = BigDecimal.valueOf(-56.5);

  @Test
  public void create_order_item_valid_arguments_succeeds() {
    OrderItem orderItem = new OrderItem(quantity, productId, productName, unitPrice);

    Assertions.assertEquals(quantity, orderItem.getQuantity());
    Assertions.assertEquals(productId, orderItem.getProductId());
    Assertions.assertEquals(productName, orderItem.getProductName());
    Assertions.assertEquals(unitPrice, orderItem.getUnitPrice());
  }

  @Test
  public void create_order_item_invalid_unit_price_fails() {
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> new OrderItem(quantity, productId, productName, negativeUnitPrice));
  }

  @Test
  public void create_order_item_invalid_quantity_fails() {
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> new OrderItem(invalidQuantity, productId, productName, unitPrice));
  }


}
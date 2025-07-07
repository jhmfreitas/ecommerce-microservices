package com.ecommerce.orderservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "T_ORDER_ITEM")
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "ORDER_ID", nullable = false)
  @JsonBackReference
  private Order order;

  @Column(name = "QUANTITY", nullable = false)
  private Integer quantity;

  @Column(name = "PRODUCT_ID", nullable = false)
  private Long productId;

  @Column(name = "PRODUCT_NAME", nullable = false)
  private String productName;

  @Column(name = "UNIT_PRICE", nullable = false)
  private BigDecimal unitPrice;

  public OrderItem() {

  }

  public OrderItem(Integer quantity, Long productId, String productName,
      BigDecimal unitPrice) {
    validateOrderItemArguments(quantity, productId, productName, unitPrice);

    this.quantity = quantity;
    this.productId = productId;
    this.productName = productName;
    this.unitPrice = unitPrice;
  }

  private static void validateOrderItemArguments(Integer quantity, Long productId,
      String productName,
      BigDecimal unitPrice) {

    if (quantity < 1) {
      throw new IllegalArgumentException("Quanity should not be less than 1");
    }

    if (productId == null) {
      throw new IllegalArgumentException("Product id should not be null");
    }

    if (productName == null || productName.isBlank()) {
      throw new IllegalArgumentException("Product name should not be blank");
    }

    if (unitPrice == null || unitPrice.signum() < 0) {
      throw new IllegalArgumentException("Unit price must be greater than or equal to zero");
    }
  }

  public Long getId() {
    return id;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }
}

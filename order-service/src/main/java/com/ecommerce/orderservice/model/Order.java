package com.ecommerce.orderservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_ORDER")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long customerId;

  @Column(nullable = false)
  private LocalDateTime orderDate;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> items = new ArrayList<>();

  public Order() {

  }

  public Order(Long customerId, LocalDateTime orderDate) {
    this.customerId = customerId;
    this.orderDate = orderDate;
  }

  public Long getId() {
    return id;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public LocalDateTime getOrderDate() {
    return orderDate;
  }

  public List<OrderItem> getItems() {
    return items;
  }

  public void addItem(OrderItem orderItem) {
    this.items.add(orderItem);
  }
}

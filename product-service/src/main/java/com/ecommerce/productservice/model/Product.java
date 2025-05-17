package com.ecommerce.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_PRODUCT")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "PRICE", nullable = false)
  private Double price;

  @Column(name = "NAME", nullable = false)
  private String name;

  public Product() {
  }

  public Product(Double price, String name) {
    this.price = price;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public Double getPrice() {
    return price;
  }

  public String getName() {
    return name;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public void setName(String name) {
    this.name = name;
  }
}


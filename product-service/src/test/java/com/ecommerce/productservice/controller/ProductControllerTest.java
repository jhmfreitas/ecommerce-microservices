package com.ecommerce.productservice.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

  @Autowired
  ProductRepository productRepository;

  // Using TestRestTemplate allows to not define URL in the requests
  @Autowired
  private TestRestTemplate testRestTemplate;

  @BeforeEach
  public void setUp() {
    for ( int i = 0; i < 10; i++) {
      Product product = new Product(i * 10.5, "Product " + i);
      productRepository.save(product);
    }
  }

  @Test
  void getProductsSuccess() {
    Product[] products = testRestTemplate.getForObject("/products",
        Product[].class);

    assertNotNull(products);
    assertEquals(10, products.length);
  }
}
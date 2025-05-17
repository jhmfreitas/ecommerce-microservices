package com.ecommerce.productservice.model;

import static org.junit.jupiter.api.Assertions.*;

import com.ecommerce.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ProductTest {

  @Autowired
  private ProductRepository productRepository;

  @Test
  void createProductTest() {
    Product product = new Product(100.0, "Product 1");
    productRepository.save(product);

    assertNotNull(product);
    assertNotNull(product.getId());
    assertEquals(100.0, product.getPrice());
    assertEquals("Product 1", product.getName());
  }

  @Test
  void findProductByNameTest() {
    Product product = new Product(100.0, "Findable Product");
    productRepository.save(product);

    Product found = productRepository.findProductByName("Findable Product");
    assertNotNull(found);
    assertEquals(product.getId(), found.getId());
    assertEquals("Findable Product", found.getName());
  }

  @Test
  void deleteProductTest() {
    Product product = new Product(100.0, "To Be Deleted");
    product = productRepository.save(product);

    productRepository.delete(product);

    boolean exists = productRepository.existsById(product.getId());
    assertFalse(exists);
  }

  @Test
  void updateProductTest() {
    Product product = new Product(100.0, "Old Name");
    product = productRepository.save(product);

    product.setName("Updated Name");
    product = productRepository.save(product);

    Product updated = productRepository.findById(product.getId()).orElse(null);
    assertNotNull(updated);
    assertEquals("Updated Name", updated.getName());
  }

  @Test
  void saveInvalidProductShouldFail() {
    assertThrows(Exception.class, () -> {
      Product invalid = new Product(null, null);
      productRepository.save(invalid);
    });
  }




}
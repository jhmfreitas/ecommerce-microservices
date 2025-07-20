package com.ecommerce.productservice.service;

import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import java.util.List;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductRepository productRepository;
  private static final Logger log = LoggerFactory.getLogger(ProductService.class);


  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> getProducts(Pageable pageable) {
    return StreamSupport.stream(productRepository.findAll(PageRequest.of(
        pageable.getPageNumber(), pageable.getPageSize(),
        pageable.getSortOr(Sort.by(Direction.ASC, "name"))
    )).spliterator(), false).toList();
  }

  @Cacheable(value = "products", key = "#id")
  public Product getProductById(Long id) {
    log.info("Fetching from DB for id: {}", id);
    return productRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
  }

  public Product createProduct(Product product) {
    if (product == null || product.getName() == null || product.getPrice() == null) {
      throw new IllegalArgumentException("Invalid product data");
    }
    return productRepository.save(product);
  }
}

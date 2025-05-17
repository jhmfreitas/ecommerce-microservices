package com.ecommerce.productservice.service;

import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import java.util.List;
import java.util.stream.StreamSupport;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> getProducts(Pageable pageable) {
    return StreamSupport.stream(productRepository.findAll(PageRequest.of(
        pageable.getPageNumber(), pageable.getPageSize(), pageable.getSortOr(Sort.by(Direction.ASC, "name"))
    )).spliterator(), false).toList();
  }
}

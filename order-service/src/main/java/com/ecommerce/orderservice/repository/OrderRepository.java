package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends CrudRepository<Order, Long>, PagingAndSortingRepository<Order, Long> {


  Order getById(Long id);
}

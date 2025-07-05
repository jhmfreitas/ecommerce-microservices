package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.repository.OrderRepository;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  private final OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public Order getOrder(Long orderId) {
    return orderRepository.getById(orderId);
  }

  public Order createOrder(Order order) {
    return orderRepository.save(order);
  }

  public List<Order> getAllOrders(Pageable pageable) {
    return orderRepository.findAll(pageable).getContent();
  }

}

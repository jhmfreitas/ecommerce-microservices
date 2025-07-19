package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.controller.dtos.CreateOrderRequestDto;
import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
    return ResponseEntity.ok(orderService.getOrder(orderId));
  }

  @GetMapping
  public ResponseEntity<Page<Order>> getAllOrders(Pageable pageable) {
    return ResponseEntity.ok(orderService.getAllOrders(pageable));
  }

  @PostMapping
  public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequestDto order) {
    return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order));
  }

}

package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.controller.dtos.CreateOrderRequestDto;
import com.ecommerce.orderservice.controller.dtos.CustomerDto;
import com.ecommerce.orderservice.controller.dtos.OrderItemDto;
import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.model.OrderItem;
import com.ecommerce.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  private static final Logger log = LoggerFactory.getLogger(OrderService.class);
  private final OrderRepository orderRepository;
  private final UserClient userClient;

  public OrderService(OrderRepository orderRepository, UserClient userClient) {
    this.orderRepository = orderRepository;
    this.userClient = userClient;
  }

  public Order getOrder(Long orderId) {
    if (orderId == null || orderId <= 0) {
      throw new IllegalArgumentException("Order ID cannot be null or negative: " + orderId);
    }

    return orderRepository.findById(orderId)
        .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + orderId)
        );
  }

  public Order createOrder(CreateOrderRequestDto createOrderRequestDto) {
    if (isCreateOrderRequestInvalid(createOrderRequestDto)) {
      log.warn("Invalid request: {}", createOrderRequestDto);
      throw new IllegalArgumentException("Invalid order request data");
    }

    CustomerDto customerDto = userClient.getCustomerById(createOrderRequestDto.customerId());
    if (customerDto == null || customerDto.name() == null) {
      throw new IllegalArgumentException(
          "Customer not found with id: " + createOrderRequestDto.customerId());
    }

    log.info("Creating order for customer {}", createOrderRequestDto.customerId());

    return orderRepository.save(mapToOrder(createOrderRequestDto));
  }

  private static boolean isCreateOrderRequestInvalid(CreateOrderRequestDto createOrderRequestDto) {
    return createOrderRequestDto == null || createOrderRequestDto.customerId() == null
        || createOrderRequestDto.orderDate() == null
        || createOrderRequestDto.orderItemDtos() == null
        || createOrderRequestDto.orderItemDtos().isEmpty();
  }

  public Page<Order> getAllOrders(Pageable pageable) {
    return orderRepository.findAll(pageable);
  }

  private static Order mapToOrder(CreateOrderRequestDto createOrderRequestDto) {
    Order order = new Order(createOrderRequestDto.customerId(), createOrderRequestDto.orderDate());
    createOrderRequestDto.orderItemDtos().forEach(i -> order.addItem(mapToOrderItem(i, order)));
    return order;
  }

  private static OrderItem mapToOrderItem(OrderItemDto i, Order order) {
    return new OrderItem(i.quantity(), i.productId(), i.productName(),
        i.unitPrice());
  }

}

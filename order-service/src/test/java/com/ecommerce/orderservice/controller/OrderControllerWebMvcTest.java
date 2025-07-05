package com.ecommerce.orderservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.model.OrderItem;
import com.ecommerce.orderservice.service.OrderService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OrderController.class)
public class OrderControllerWebMvcTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private OrderService orderService;

  @Test
  public void getOrderSuccess() throws Exception {
    List<Order> orders = new ArrayList<>();
    BigDecimal unitPrice = BigDecimal.valueOf(45.25);
    String productName = "Product Name";
    long productId = 1L;
    int quantity = 2;
    Long customerId = 2L;

    OrderItem orderItem = new OrderItem(quantity, productId, productName, unitPrice);
    LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    String formattedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"));

    Order order = new Order(customerId, now);
    order.addItem(orderItem);
    orders.add(order);

    when(orderService.getAllOrders(any(Pageable.class))).thenReturn(orders);

    mockMvc.perform(get("/order")).andExpect(status().isOk())
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].customerId").value(customerId))
        .andExpect(jsonPath("$[0].orderDate").value(formattedNow))
        .andExpect(jsonPath("$[0].items.[0].quantity").value(quantity))
        .andExpect(jsonPath("$[0].items.[0].productId").value(productId))
        .andExpect(jsonPath("$[0].items.[0].productName").value(productName))
        .andExpect(jsonPath("$[0].items.[0].unitPrice").value(unitPrice))
    ;
  }
}

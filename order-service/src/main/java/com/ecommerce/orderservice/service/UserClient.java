package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.controller.dtos.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {

  @GetMapping("/users/{id}")
  CustomerDto getCustomerById(@PathVariable("id") Long id);

}

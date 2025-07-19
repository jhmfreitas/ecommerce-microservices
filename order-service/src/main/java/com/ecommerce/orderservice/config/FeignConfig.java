package com.ecommerce.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

  @Bean
  public feign.Logger.Level feignLoggerLevel() {
    return feign.Logger.Level.FULL;
  }
}

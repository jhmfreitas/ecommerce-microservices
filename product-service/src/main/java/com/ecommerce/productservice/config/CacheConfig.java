// src/main/java/com/ecommerce/orderservice/config/CacheConfig.java
package com.ecommerce.productservice.config;

import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

  @Bean
  public CacheErrorHandler cacheErrorHandler() {
    return new CacheErrorHandler() {
      @Override
      public void handleCacheGetError(RuntimeException exception,
          org.springframework.cache.Cache cache, Object key) {
        System.err.println("[Cache Get Error] Key: " + key + " | " + exception.getMessage());
      }

      @Override
      public void handleCachePutError(RuntimeException exception,
          org.springframework.cache.Cache cache, Object key, Object value) {
        System.err.println("[Cache Put Error] Key: " + key + " | " + exception.getMessage());
      }

      @Override
      public void handleCacheEvictError(RuntimeException exception,
          org.springframework.cache.Cache cache, Object key) {
        System.err.println("[Cache Evict Error] Key: " + key + " | " + exception.getMessage());
      }

      @Override
      public void handleCacheClearError(RuntimeException exception,
          org.springframework.cache.Cache cache) {
        System.err.println("[Cache Clear Error] " + exception.getMessage());
      }
    };
  }
}

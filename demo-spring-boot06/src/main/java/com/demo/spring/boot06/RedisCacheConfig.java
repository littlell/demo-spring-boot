package com.demo.spring.boot06;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.util.Arrays;

@Configuration
@ConditionalOnProperty(name = "cache.type", havingValue = "redis")
public class RedisCacheConfig {

  @Bean
  public CacheManager cacheManager(RedisConnectionFactory factory) {

    RedisCacheManager cacheManager = RedisCacheManager.builder(factory).transactionAware().build();

    return cacheManager;
  }

}

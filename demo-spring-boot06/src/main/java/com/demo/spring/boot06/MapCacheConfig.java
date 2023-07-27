package com.demo.spring.boot06;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ConditionalOnProperty(name = "cache.type", havingValue= "map")
public class MapCacheConfig {

  @Bean
  public CacheManager cacheManager() {
    SimpleCacheManager cacheManager = new SimpleCacheManager();

    // 配置缓存名称和缓存对象
    ConcurrentMapCache cache1 = new ConcurrentMapCache("cache1");
    ConcurrentMapCache cache2 = new ConcurrentMapCache("cache2");

    cacheManager.setCaches(Arrays.asList(cache1, cache2));

    System.out.println("Map cache init.");

    return cacheManager;
  }

}

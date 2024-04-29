package com.demo.spring.boot06.config;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
@ConditionalOnProperty(name = "cache.type", havingValue = "ehcache")
public class EhCacheConfig {

//  @Bean
//  public CacheManager cacheManager() {

//    net.sf.ehcache.CacheManager ehCacheManager = new net.sf.ehcache.CacheManager();
//    ehCacheManager.addCache("cache1");
//    ehCacheManager.addCache("cache2");
//    EhCacheCacheManager cacheManager = new EhCacheCacheManager();
//
//    cacheManager.setCacheManager(ehCacheManager);
//
//    System.out.println("Ehcache cache init.");
//
//    return cacheManager;


  @Bean
  public CacheManager cacheManager() {
    CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);

    CacheConfigurationBuilder<String, String> cacheConfig = CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class,
            // 根据需要配置资源池
            ResourcePoolsBuilder.heap(100)
        )
        // 设置过期策略
        .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.of(10, ChronoUnit.MINUTES)));

    cacheManager.createCache("cache1", cacheConfig);
    cacheManager.createCache("cache2", cacheConfig);

    System.out.println("Ehcache cache init.");
    return cacheManager;
  }
}


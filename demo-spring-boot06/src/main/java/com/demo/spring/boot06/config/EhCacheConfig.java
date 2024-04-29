package com.demo.spring.boot06.config;

import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@ConditionalOnProperty(name = "cache.type", havingValue = "ehcache")
public class EhCacheConfig {

  @Autowired
  private CacheProperties cacheProperties;

  @Bean
  public CacheManager cacheManager() {
    CacheManagerBuilder builder = CacheManagerBuilder.newCacheManagerBuilder();

    // 配置一个缓存
    builder.withCache("cache1",
        CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class,
                ResourcePoolsBuilder.heap(100))
            .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(1)))
            .build());

    CacheManager cacheManager = builder.build();
    cacheManager.init();
    return cacheManager;
  }
}


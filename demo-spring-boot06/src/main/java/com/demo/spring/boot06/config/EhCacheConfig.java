package com.demo.spring.boot06.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "cache.type", havingValue = "ehcache")
public class EhCacheConfig {

  @Bean
  public CacheManager cacheManager() {

    net.sf.ehcache.CacheManager ehCacheManager = new net.sf.ehcache.CacheManager();
    ehCacheManager.addCache("cache1");
    ehCacheManager.addCache("cache2");
    EhCacheCacheManager cacheManager = new EhCacheCacheManager();

    cacheManager.setCacheManager(ehCacheManager);

    System.out.println("Ehcache cache init.");

    return cacheManager;
  }

}

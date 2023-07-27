package com.demo.spring.boot06;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

  @Cacheable("myCache")
  public String cacheable(String key) {
    System.out.println("Fetching data for key: " + key);
    // 模拟缓慢的数据访问
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "Cached data for " + key;
  }

  @CachePut("myCache")
  public String cachePut(String key) {
    System.out.println("Fetching data for key: " + key);
    // 模拟缓慢的数据访问
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "Cached data for " + key;
  }

  @CacheEvict(cacheNames = "myCache", key = "#key")
  public void clearCacheItem(String key) {
    // 执行清除缓存项的逻辑
  }

  @CacheEvict(cacheNames = "myCache", allEntries = true)
  public void clearCache() {
    // 执行清除整个缓存的逻辑
  }

  @Caching(cacheable = {@Cacheable(cacheNames = "cache1", key = "#id"), @Cacheable(cacheNames = "cache2", key = "#name")})
  public String caching(Long id, String name) {
    // 从数据库中获取用户信息
    System.out.println("Fetching data for id=" + id + ", name=" + name);

    return "person";
  }
}

package com.demo.spring.boot01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {
  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @RequestMapping("/set")
  public String set(String key, String value) {
    return null;
  }

  @RequestMapping("/get")
  public String get(@RequestParam String key) {
    String value = stringRedisTemplate.opsForValue().get(key);
    return value;
  }
}

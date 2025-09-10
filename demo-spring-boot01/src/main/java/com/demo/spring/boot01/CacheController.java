package com.demo.spring.boot01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {
  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @GetMapping("/set")
  public String set(@RequestParam String key, @RequestParam String value) {
    stringRedisTemplate.opsForValue().set(key, value);
    return "success";
  }

  @GetMapping("/get")
  public String get(@RequestParam String key) {
    return stringRedisTemplate.opsForValue().get(key);
  }
}

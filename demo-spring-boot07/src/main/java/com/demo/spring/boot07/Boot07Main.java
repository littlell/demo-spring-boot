package com.demo.spring.boot07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Boot07Main {
  public static void main(String[] args) {
    SpringApplication.run(Boot07Main.class, args);
  }
}

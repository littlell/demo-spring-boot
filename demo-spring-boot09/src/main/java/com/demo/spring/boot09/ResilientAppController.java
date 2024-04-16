package com.demo.spring.boot09;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api/")
public class ResilientAppController {

  @Autowired
  ExternalAPICaller externalAPICaller;

  @GetMapping("/circuit-breaker")
  @CircuitBreaker(name = "CircuitBreakerService")
  public String circuitBreakerApi() {
    return externalAPICaller.callApi();
  }
}
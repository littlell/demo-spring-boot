package com.demo.spring.boot01;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
  @GetMapping
  public String get() {
    return null;
  }

  @PostMapping
  public String post() {
    return null;
  }

  @PutMapping
  public String put() {
    return null;
  }

  @DeleteMapping
  public String delete() {
    return null;
  }
}

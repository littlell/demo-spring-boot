package com.demo.spring.boot01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/env")
public class EnvController {

  @Value("${foo}")
  private String foo;

  @GetMapping
  ResponseEntity<String> p() {
    String content = "Hello, " + foo;
    return new ResponseEntity<>(content, HttpStatus.OK);
  }
}



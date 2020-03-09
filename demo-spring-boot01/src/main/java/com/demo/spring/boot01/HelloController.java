package com.demo.spring.boot01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @Value("${foo}")
  private String foo;

  @RequestMapping("/hello")
  ResponseEntity<String> home() {
    String content = "Hello, " + foo;
    ResponseEntity<String> responseEntity = new ResponseEntity<String>(content, HttpStatus.OK);
    return responseEntity;
  }
}



package com.demo.spring.boot10;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

  @RequestMapping("/demo")
  public ResponseEntity<String> demo() {
    String body = "demo";
    return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.OK);
  }

  @RequestMapping("/new/prefix-path")
  public ResponseEntity<String> newPrefixPath() {
    String body = "new-prefix-path";
    return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.OK);
  }
}

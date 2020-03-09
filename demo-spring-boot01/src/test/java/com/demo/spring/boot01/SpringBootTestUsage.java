package com.demo.spring.boot01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootTestUsage {
  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  public void simple() {
    String body = this.testRestTemplate.getForObject("/hello", String.class);
    assertThat(body).isEqualTo("Hello, bar");
  }
}

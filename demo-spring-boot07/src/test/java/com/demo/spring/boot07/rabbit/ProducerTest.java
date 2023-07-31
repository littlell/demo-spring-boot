package com.demo.spring.boot07.rabbit;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProducerTest {

  @Autowired
  private AmqpTemplate amqpTemplate;

  @Test
  public void send() {

    amqpTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE_NAME, RabbitConfig.TEST_ROUTE_NAME, "hello");
  }
}

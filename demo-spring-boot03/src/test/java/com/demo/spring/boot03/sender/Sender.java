package com.demo.spring.boot03.sender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Sender {
  @Autowired
  AmqpTemplate amqpTemplate;

  //exchange交换器名称
  @Value("${mq.config.exchange.topic}")
  private String topicExchange;

  @Value("${mq.config.route.topic}")
  private String topicRouteName;

  @Test
  public void send() throws InterruptedException {
    amqpTemplate.convertAndSend(topicExchange, topicRouteName, "product...debug========" + "Hello");
  }
}

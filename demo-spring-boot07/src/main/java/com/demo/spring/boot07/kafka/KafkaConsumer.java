package com.demo.spring.boot07.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

  @KafkaListener(topics = KafkaConfig.TEST_TOPIC)
  public void receiveMessage(String message) {
    System.out.println("Received message: " + message);
  }
}

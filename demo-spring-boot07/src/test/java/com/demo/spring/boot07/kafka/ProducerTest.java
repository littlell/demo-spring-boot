package com.demo.spring.boot07.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
public class ProducerTest {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Test
  public void send() {

    kafkaTemplate.send(KafkaConfig.TEST_TOPIC, "hello1");
  }
}

package com.demo.spring.boot07.rabbit;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsumer {

  @RabbitListener(queues = RabbitConfig.TEST_QUEUE_NAME)
  public void receiveMessage(String message) {
    System.out.println("Received message: " + message);
  }
}

package com.demo.spring.boot04.receiver;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(
    bindings = @QueueBinding(
        value = @Queue(value = "${mq.config.queue.topic}", autoDelete = "false"),
        exchange = @Exchange(value = "${mq.config.exchange.topic}", type = ExchangeTypes.TOPIC),
        key = "${mq.config.route.topic}"
    )
)
public class TopicReceiver {

  @RabbitHandler
  public void process(Object msg) {
    System.out.println("Info ............receiver" + msg);
  }

}

package com.demo.spring.boot08.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class GreetingController {

  /**
   * rsocket-cli --request -i hello --route=greeting  ws://127.0.0.1:8080/rsocket
   */
  @MessageMapping("greeting")
  public Mono<String> handleGreeting(Mono<String> greetingMono) {
    return greetingMono
        .doOnNext(greeting ->
            log.info("Received a greeting: {}", greeting))
        .map(greeting -> "Hello back to you!");
  }

  /**
   * rsocket-cli --request -i hello --route=greeting/littlell  ws://127.0.0.1:8080/rsocket
   */
  @MessageMapping("greeting/{name}")
  public Mono<String> handleGreetingName(
      @DestinationVariable("name") String name,
      Mono<String> greetingMono) {
    return greetingMono
        .doOnNext(greeting ->
            log.info("Received a greeting from {} : {}", name, greeting))
        .map(greeting -> "Hello back to you!");
  }
}

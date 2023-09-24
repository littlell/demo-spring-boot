package com.demo.spring.boot08.controller;

import com.demo.spring.boot08.dto.Alert;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class AlertController {

  @MessageMapping("alert")
  public Mono<Void> sendAlert(Mono<Alert> alertMono) {
    return alertMono
        .doOnNext(alert -> {
          log.info("{} alert ordered by {} at {}",
              alert.getLevel(),
              alert.getOrderedBy(),
              alert.getOrderedAt());
        })
        .thenEmpty(Mono.empty());
  }
}

package com.demo.spring.boot08.controller;

import com.demo.spring.boot08.dto.StockQuote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

  @Autowired
  private RSocketRequester rSocketRequester;

  @GetMapping("/greeting")
  public Mono<String> handleGreeting() {
    return rSocketRequester
        .route("greeting")
        .data("Hello RSocket!")
        .retrieveMono(String.class);
  }

  @GetMapping("/greeting-name")
  public Mono<String> handleGreetingName() {
    String who = "littlell";
    return rSocketRequester
        .route("greeting/{name}", who)
        .data("Hello RSocket!")
        .retrieveMono(String.class);
  }

  @GetMapping("/stock-price")
  public String stockPrice() {
    String symbol = "sk";
    rSocketRequester
        .route("stock/{symbol}", symbol)
        .retrieveFlux(StockQuote.class)
        .doOnNext(stockQuote ->
            log.info("Price of {} : {} (at {})", stockQuote.getSymbol(), stockQuote.getPrice(), stockQuote.getTimestamp())
        );
    return "success";
  }
}

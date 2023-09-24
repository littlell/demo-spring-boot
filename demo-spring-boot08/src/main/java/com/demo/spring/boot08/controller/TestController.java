package com.demo.spring.boot08.controller;

import com.demo.spring.boot08.dto.Alert;
import com.demo.spring.boot08.dto.GratuityIn;
import com.demo.spring.boot08.dto.GratuityOut;
import com.demo.spring.boot08.dto.StockQuote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

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
        )
        .subscribe();
    return "success";
  }

  @GetMapping("/alert")
  public String sendAlert() {
    rSocketRequester
        .route("alert")
        .data(new Alert(Alert.Level.RED, "littlell", Instant.now()))
        .send()
        .subscribe();
    return "success";
  }

  @GetMapping("/gratuity")
  public String calculateGratuity() throws InterruptedException {

    Flux<GratuityIn> gratuityInFlux = Flux.fromArray(
            new GratuityIn[]{
                new GratuityIn(BigDecimal.valueOf(11.0), 10),
                new GratuityIn(BigDecimal.valueOf(22.0), 20),
                new GratuityIn(BigDecimal.valueOf(33.0), 30),
                new GratuityIn(BigDecimal.valueOf(44.0), 40),
                new GratuityIn(BigDecimal.valueOf(55.0), 50),
                new GratuityIn(BigDecimal.valueOf(66.0), 60)
            })
        .delayElements(Duration.ofSeconds(1));

    rSocketRequester
        .route("gratuity")
        .data(gratuityInFlux)
        .retrieveFlux(GratuityOut.class)
        .subscribe(out -> {
          log.info(out.getPercent() + "% gratuity of " + out.getBillTotal() + " is " + out.getGratuity());
        });
    return "success";
  }
}

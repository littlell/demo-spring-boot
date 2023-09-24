package com.demo.spring.boot08.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeTypeUtils;

import java.time.Duration;

import reactor.util.retry.Retry;

@Configuration
public class ClientConfiguration {
  @Bean
  public RSocketRequester rSocketRequester() {
    RSocketRequester.Builder builder = RSocketRequester.builder();

    return builder
        .rsocketConnector(
            rSocketConnector ->
                rSocketConnector.reconnect(Retry.fixedDelay(2, Duration.ofSeconds(2)))
        )
        .dataMimeType(MimeTypeUtils.APPLICATION_JSON)
        .tcp("localhost", 7000);
  }
}

package com.demo.spring.boot10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    // 这里定义路由配置，与application.yml中的配置相同
    return builder.routes()
        .route("backend_service",
            r -> r.path("/backend/**")
                .filters(f -> f.rewritePath("/backend/(?<segment>.*)", "/$\\{segment}"))
                .uri("http://localhost:8080")
        )
        .build();
  }
}
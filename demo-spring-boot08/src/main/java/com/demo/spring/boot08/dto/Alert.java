package com.demo.spring.boot08.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alert {

  private Level level;
  private String orderedBy;
  private Instant orderedAt;

  public enum Level {
    YELLOW, ORANGE, RED, BLACK;
  }
}

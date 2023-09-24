package com.demo.spring.boot08.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockQuote implements Serializable {

  private String symbol;
  private BigDecimal price;
  private Instant timestamp;
}

package com.demo.spring.boot08.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GratuityOut {

  private BigDecimal billTotal;

  private int percent;

  private BigDecimal gratuity;
}

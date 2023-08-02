package com.demo.spring.boot06.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserMix {

  @JsonCreator
  public UserMix(@JsonProperty("name") String name, @JsonProperty("age") Integer age) {
  }
}

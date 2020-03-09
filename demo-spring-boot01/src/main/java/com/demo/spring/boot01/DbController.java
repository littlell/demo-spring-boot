package com.demo.spring.boot01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/db")
public class DbController {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @RequestMapping("/test")
  public Integer test() {
    Integer count = jdbcTemplate.queryForObject("select count(*) from user;", Integer.class);
    return count;
  }

  @RequestMapping("/test1")
  public int test1() {
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("name", "foo");
    paramMap.put("age", 12);
    int resp = namedParameterJdbcTemplate.update("insert into user (name, age) values(:name, :age)", paramMap);
    return resp;
  }

  /**
   * find user by name, and update new age.
   */
  @RequestMapping("/test3")
  public int test3(String name, int age) {
    return 1;
  }
}

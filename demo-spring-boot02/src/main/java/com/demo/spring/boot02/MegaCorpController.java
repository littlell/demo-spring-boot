package com.demo.spring.boot02;

import org.elasticsearch.client.RequestOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/megacorp")
public class MegaCorpController {

  @Autowired
  private MegaCorpRepository megaCorpRepository;

  @Autowired
  private ElasticsearchTemplate elasticsearchTemplate;

  @GetMapping("/and")
  public Object and() {
    return megaCorpRepository.findByFirstNameAndLastName("Jane", "Smith");
  }

  @GetMapping("/or")
  public Object or() {
    return megaCorpRepository.findByFirstNameOrLastName("Jane", "Jane");
  }

  @GetMapping("/is")
  public Object is() {
    return megaCorpRepository.findByFirstName("Jane");
  }

  @GetMapping("/not")
  public Object not() {
    return megaCorpRepository.findByFirstNameNot("Jane");
  }

  @GetMapping("/between")
  public Object between() {
    return megaCorpRepository.findByAgeBetween(1, 30);
  }

  @GetMapping("/lessThanEqual")
  public Object lessThanEqual() {
    return megaCorpRepository.findByAgeLessThanEqual(100);
  }

  @GetMapping("/greaterThanEqual")
  public Object greaterThanEqual() {
    return megaCorpRepository.findByAgeGreaterThanEqual(1);
  }

  @GetMapping("/before")
  public Object before() {
    return megaCorpRepository.findByAgeBefore(100);
  }

  @GetMapping("/after")
  public Object after() {
    return megaCorpRepository.findByAgeAfter(1);
  }

  @GetMapping("/like")
  public Object like() {
    return megaCorpRepository.findByFirstNameLike("Jan");
  }

  @GetMapping("/startingWith")
  public Object startingWith() {
    return megaCorpRepository.findByFirstNameStartingWith("Ja");
  }

  @GetMapping("/endingWith")
  public Object endingWith() {
    return megaCorpRepository.findByFirstNameEndingWith("ne");
  }

  @GetMapping("/containing")
  public Object containing() {
    return megaCorpRepository.findByFirstNameContaining("an");
  }

  @GetMapping("/in")
  public Object in() {
    return megaCorpRepository.findByFirstNameIn(new ArrayList<String>() {
      {
        add("a");
        add("b");
        add("Jane");
      }
    });
  }

  @GetMapping("/notIn")
  public Object notIn() {
    return megaCorpRepository.findByFirstNameNotIn(new ArrayList<String>() {
      {
        add("a");
        add("b");
        add("Jane");
      }
    });
  }

  @GetMapping("/orderBy")
  public Object orderBy() {
    return megaCorpRepository.findByAgeGreaterThanEqualOrderByAgeAsc(1);
  }

  @GetMapping("/annotation")
  public Object annotation() {
    return megaCorpRepository.findByLastName("Smith");
  }

  /**
   * Dynamic query
   */
//  @GetMapping("/search/dynamic")
//  public Object dynamic() throws IOException {
//    SearchRequest searchRequest = new SearchRequest("megacorp");
//    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//    boolQueryBuilder.must(QueryBuilders.termQuery("lastName", "smith"));
//    boolQueryBuilder.must(QueryBuilders.termQuery("firstName", "John"));
//    searchSourceBuilder.query(boolQueryBuilder);
//    searchRequest.source(searchSourceBuilder);
//    SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//
//    return searchResponse;
//  }

}

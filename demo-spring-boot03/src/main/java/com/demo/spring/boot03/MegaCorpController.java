package com.demo.spring.boot03;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

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
   * Analyzed Fields
   */
  @GetMapping("/search/analyzedFields")
  public Object analyzedFields() {
    SearchQuery searchQuery = new NativeSearchQueryBuilder()
        .withQuery(matchQuery("firstName", "jane"))
        .build();

    return megaCorpRepository.search(searchQuery);
  }

  /**
   * Not Analyzed Fields
   */
  @GetMapping("/search/notAnalyzedFields")
  public Object notAnalyzedFields() {
    SearchQuery searchQuery = new NativeSearchQueryBuilder()
        .withQuery(matchQuery("about", "collect"))
        .build();

    return megaCorpRepository.search(searchQuery);
  }

  /**
   * Not Analyzed Fields
   */
  @GetMapping("/search/aggregations")
  public Object aggregations() {
    SearchQuery searchQuery = new NativeSearchQueryBuilder()
        .addAggregation((AggregationBuilders.sum("ageTotal").field("age"))).build();


    return elasticsearchTemplate.query(searchQuery, (ResultsExtractor<Object>) searchResponse -> searchResponse.getAggregations());
  }
}

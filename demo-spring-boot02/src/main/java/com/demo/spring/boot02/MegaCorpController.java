package com.demo.spring.boot02;

import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * MegaCorp Controller - 优化使用构造函数注入和现代语法
 * 移除了 @Autowired 字段注入，使用构造函数注入
 * 使用 List.of() 替代匿名内部类
 */
@RestController
@RequestMapping("/megacorp")
public class MegaCorpController {

  private final MegaCorpRepository megaCorpRepository;
  private final ElasticsearchTemplate elasticsearchTemplate;

  public MegaCorpController(MegaCorpRepository megaCorpRepository,
                            ElasticsearchTemplate elasticsearchTemplate) {
    this.megaCorpRepository = megaCorpRepository;
    this.elasticsearchTemplate = elasticsearchTemplate;
  }

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
    return megaCorpRepository.findByFirstNameIn(List.of("a", "b", "Jane"));
  }

  @GetMapping("/notIn")
  public Object notIn() {
    return megaCorpRepository.findByFirstNameNotIn(List.of("a", "b", "Jane"));
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
   * Dynamic query - 这是一个动态查询的示例
   * 注释掉的代码展示了如何使用原生 Elasticsearch 客户端进行复杂查询
   */
  /*
  @GetMapping("/search/dynamic")
  public Object dynamic() throws IOException {
    SearchRequest searchRequest = new SearchRequest("megacorp");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    boolQueryBuilder.must(QueryBuilders.termQuery("lastName", "smith"));
    boolQueryBuilder.must(QueryBuilders.termQuery("firstName", "John"));
    searchSourceBuilder.query(boolQueryBuilder);
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

    return searchResponse;
  }
  */
}

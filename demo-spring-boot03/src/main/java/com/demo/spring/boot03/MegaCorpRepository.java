package com.demo.spring.boot03;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.ArrayList;
import java.util.List;

public interface MegaCorpRepository extends ElasticsearchRepository<MegaCorp, String> {

  List<MegaCorp> findByFirstNameAndLastName(String jane, String smith);

  List<MegaCorp> findByFirstNameOrLastName(String jane, String jane1);

  List<MegaCorp> findByFirstName(String jane);

  List<MegaCorp> findByFirstNameNot(String jane);

  List<MegaCorp> findByAgeBetween(int i, int i1);

  List<MegaCorp> findByAgeLessThanEqual(int i);

  List<MegaCorp> findByAgeGreaterThanEqual(int i);

  List<MegaCorp> findByAgeBefore(int i);

  List<MegaCorp> findByAgeAfter(int i);

  List<MegaCorp> findByFirstNameLike(String jan);

  List<MegaCorp> findByFirstNameStartingWith(String ja);

  List<MegaCorp> findByFirstNameEndingWith(String ne);

  List<MegaCorp> findByFirstNameContaining(String an);

  List<MegaCorp> findByFirstNameIn(ArrayList<String> strings);

  List<MegaCorp> findByFirstNameNotIn(ArrayList<String> strings);

  List<MegaCorp> findByAgeGreaterThanEqualOrderByAgeAsc(int i);

  @Query("{\"bool\" : {\"must\" : {\"match\" : {\"lastName\" : \"?0\"}}}}")
  List<MegaCorp> findByLastName(String firstName);
}

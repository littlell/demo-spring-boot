package com.demo.spring.boot03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class MegaCorpService {

  @Autowired
  private MegaCorpRepository megaCorpRepository;

  @PostConstruct
  public void init() {
    megaCorpRepository.save(new MegaCorp("1", "John", "Smith", 25, "I love to go rock climbing", new ArrayList<String>() {{
      add("sports");
      add("music");
    }}));
    megaCorpRepository.save(new MegaCorp("2", "Jane", "Smith", 32, "I like to collect rock albums", new ArrayList<String>() {{
      add("music");
    }}));
    megaCorpRepository.save(new MegaCorp("3", "Douglas", "Fir", 35, "I like to build cabinets", new ArrayList<String>() {{
      add("forestry");
    }}));
  }

  @PreDestroy
  public void destroy() {
    megaCorpRepository.deleteAll();
  }
}

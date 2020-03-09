package com.demo.spring.boot01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loader")
public class LoaderController {

  private final Logger LOGGER = LoggerFactory.getLogger(getClass());

  @RequestMapping("/chain")
  public void chain() {
    ClassLoader loader = getClass().getClassLoader();

    while (loader != null) {
      System.out.println(loader.getClass().getCanonicalName());

      loader = loader.getParent();
    }
  }
}

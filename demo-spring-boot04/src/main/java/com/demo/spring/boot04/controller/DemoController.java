package com.demo.spring.boot04.controller;

import com.demo.spring.boot04.repository.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private VetRepository vetRepository;

    @GetMapping("/search")
    public Object search() {
        return vetRepository.findAll();
    }

}

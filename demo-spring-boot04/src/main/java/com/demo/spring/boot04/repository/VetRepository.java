package com.demo.spring.boot04.repository;

import com.demo.spring.boot04.entity.Vet;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface VetRepository extends CrudRepository<Vet, UUID> {

    Vet findByFirstName(String username);
}
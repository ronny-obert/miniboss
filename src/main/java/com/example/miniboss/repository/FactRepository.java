package com.example.miniboss.repository;

import com.example.miniboss.model.Fact;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class FactRepository {
    RestTemplate restTemplate = new RestTemplate();

    public Optional<Fact> findRandom() {
        Fact fact = new Fact();
        try {
            fact = restTemplate.getForObject("https://cat-fact.herokuapp.com/facts/random", Fact.class);
            return Optional.of(fact);
        }
        catch (Exception e) {
            System.out.println(e);
            return Optional.of(fact);
        }
    }

    public Optional<Fact> findAllById(String id) {
        Fact fact = new Fact();
        try {
            fact = restTemplate.getForObject("https://cat-fact.herokuapp.com/facts/" + id, Fact.class);
            return Optional.of(fact);
        }
        catch (Exception e) {
            System.out.println(e);
            return Optional.of(fact);
        }
    }
}

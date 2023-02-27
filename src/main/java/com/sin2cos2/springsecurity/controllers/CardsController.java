package com.sin2cos2.springsecurity.controllers;

import com.sin2cos2.springsecurity.models.Cards;
import com.sin2cos2.springsecurity.repositories.CardsRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class CardsController {

    private final CardsRepository cardsRepository;

    @GetMapping("/cards")
    public List<Cards> getCardDetails(@RequestParam int id) {
        return cardsRepository.findByCustomerId(id);
    }
}

package com.easybytes.controller;

import com.easybytes.model.Cards;
import com.easybytes.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;

    @GetMapping("/myCards")
    public List<Cards> getCardDetails(@RequestParam int id){
        List<Cards> cards = this.cardsRepository.findByCustomerId(id);
        if(!cards.isEmpty()){
            return cards;
        }else {
            return null;
        }
    }
}

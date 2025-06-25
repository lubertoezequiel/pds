package com.g4.tp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g4.tp.DTOs.SportDTO;
import com.g4.tp.model.entities.Sport;
import com.g4.tp.service.SportService;

@RestController
@RequestMapping("/sport")
public class SportController {

    @Autowired
    private SportService sportService;

    @GetMapping("/hello")
    public String hello() {
        return "Hola deporte";
    }

    @PostMapping("/create")
    public SportDTO createSport(@RequestBody SportDTO sport) {
        System.out.println("Creating sport: " + sport.getName() +
                " with description: " + sport.getDescription() +
                " and required players: " + sport.getRequiredPlayers());

        Sport deporte = new Sport(sport.getName(), sport.getDescription(), sport.getRequiredPlayers());

        sportService.createSport(deporte);

        return sport;
    }

}
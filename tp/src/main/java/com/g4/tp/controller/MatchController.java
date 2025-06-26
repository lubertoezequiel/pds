package com.g4.tp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g4.tp.DTOs.MatchDTO;
import com.g4.tp.model.entities.Match;
import com.g4.tp.service.MatchService;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/hello")
    public String hello() {
        return "Hola partido";
    }

    @PostMapping("/create")
    public MatchDTO createMatch(@RequestBody MatchDTO match) {
        System.out.println("Creating match: " + match.getSport() +
                " on date: " + match.getDate() +
                " duration: " + match.getDuration() + " minutes");

        Match partido = new Match(match.getSport(), match.getDuration(), match.getDate(), match.getTime());

        matchService.createMatch(partido);

        return match;
    }

    // TODO: Agregar endpoints para:
    // - GET /search - Buscar partidos disponibles
    // - POST /join/{id} - Unirse a un partido
    // - PUT /confirm/{id} - Confirmar partido
    // - PUT /cancel/{id} - Cancelar partido

}